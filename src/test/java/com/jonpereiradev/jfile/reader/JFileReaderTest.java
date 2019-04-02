package com.jonpereiradev.jfile.reader;

import com.jonpereiradev.jfile.reader.configuration.ReaderConfiguration;
import com.jonpereiradev.jfile.reader.file.JFileLine;
import com.jonpereiradev.jfile.reader.infrastructure.AbstractFileReaderTest;
import com.jonpereiradev.jfile.reader.model.Example;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;

public class JFileReaderTest extends AbstractFileReaderTest {

    @Test
    public void mustCreateImplementationPerFileType() throws IOException {
        Path path = Files.createTempFile("tmp", "1");

        try (JFileReader fileReader = JFileReaderFactory.newInstance(path, ReaderConfiguration.utf8Reader("."))) {
            Assert.assertEquals(JFileReaderImpl.class, fileReader.getClass());
        }
    }

    @Test
    public void mustReportFileNotExists() throws IOException {
        Path path = Paths.get("file-not-found.csv");
        ReaderConfiguration readerConfiguration = ReaderConfiguration.utf8Reader(".");

        try (JFileReader fileReader = JFileReaderFactory.newInstance(path, readerConfiguration)) {
            Assert.fail("File must not exists");
        } catch (IllegalArgumentException e) {
            Assert.assertEquals("java.nio.file.NoSuchFileException: file-not-found.csv", e.getMessage());
        }
    }

    @Test
    public void mustReportFileExists() throws IOException {
        Path path = Files.createTempFile("tmp", "1");

        try (JFileReader fileReader = JFileReaderFactory.newInstance(path, ReaderConfiguration.utf8Reader("."))) {
            Assert.assertNotNull(fileReader);
        } catch (IllegalArgumentException e) {
            Assert.fail("File must exists");
        }
    }

    @Test
    public void mustReadFileByConfiguration() throws IOException {
        Path path = createFileWithContent("1;2;19121991");

        ReaderConfiguration configuration = ReaderConfiguration
            .utf8Reader("\\;")
            .withLocalDateFormatter(DateTimeFormatter.ofPattern("ddMMyyyy"));

        try (JFileReader fileReader = JFileReaderFactory.newInstance(path, configuration)) {
            Iterator<JFileLine> iterator = fileReader.iterator();

            Assert.assertTrue("Deve retornar uma linha do arquivo.", iterator.hasNext());

            JFileLine fileLine = iterator.next();
            Assert.assertEquals("1;2;19121991", fileLine.getContent());
            Assert.assertEquals(3, fileLine.getColumns().size());

            Assert.assertFalse("Não deve retornar porque o arquivo só tem uma linha.", iterator.hasNext());
        }
    }

    @Test
    public void mustParserFileContentToObject() throws IOException {
        Path path = createFileWithContent("1;descricao;1;1;100,37;93,42;19121991;justificativa");
        ReaderConfiguration configuration = ReaderConfiguration.utf8Reader("\\;");

        try (JFileReader fileReader = JFileReaderFactory.newInstance(path, configuration)) {
            fileReader.forEach(Example.class, example -> {
                Short id = (short) 1;

                Assert.assertEquals(id, example.getTipoDispendio());
                Assert.assertEquals("descricao", example.getDescricao());
                Assert.assertEquals(id, example.getTipoApropriacao());
                Assert.assertEquals(id, example.getOutroTipoApropriacao());
                Assert.assertEquals(new BigDecimal("100.37"), example.getValorDispendio());
                Assert.assertEquals(new BigDecimal("93.42"), example.getValorDispendioDepreciacao());
                Assert.assertEquals(LocalDate.parse("1991-12-19"), example.getDataAquisicao());
                Assert.assertEquals("justificativa", example.getJustificativa());
            });
        }
    }
}
