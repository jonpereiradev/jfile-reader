/*
 * MIT License
 *
 * Copyright (c) 2020 Jonathan de Almeida Pereira
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package com.jonpereiradev.jfile.reader;

import com.jonpereiradev.jfile.reader.file.LineValue;
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
        JFileReaderConfig configuration = JFileReaderFactory.newUtf8ReaderConfig(".");

        try (JFileReader fileReader = JFileReaderFactory.newJFileReader(path, configuration)) {
            Assert.assertEquals(JFileReaderEngine.class, fileReader.getClass());
        }
    }

    @Test
    public void mustReportFileNotExists() {
        Path path = Paths.get("file-not-found.csv");
        JFileReaderConfig readerConfig = JFileReaderFactory.newUtf8ReaderConfig(".");

        try (JFileReader fileReader = JFileReaderFactory.newJFileReader(path, readerConfig)) {
            Assert.fail("File must not exists");
        } catch (IOException e) {
            Assert.assertEquals("file-not-found.csv", e.getMessage());
        }
    }

    @Test
    public void mustReportFileExists() throws IOException {
        Path path = Files.createTempFile("tmp", "1");
        JFileReaderConfig readerConfig = JFileReaderFactory.newUtf8ReaderConfig(".");

        try (JFileReader fileReader = JFileReaderFactory.newJFileReader(path, readerConfig)) {
            Assert.assertNotNull(fileReader);
        } catch (IllegalArgumentException e) {
            Assert.fail("File must exists");
        }
    }

    @Test
    public void mustReadFileByConfiguration() throws IOException {
        Path path = createFileWithContent("1;2;19121991");

        JFileReaderConfig configuration = JFileReaderFactory
            .newUtf8ReaderConfig("\\;")
            .localDateFormatter(DateTimeFormatter.ofPattern("ddMMyyyy"));

        try (JFileReader fileReader = JFileReaderFactory.newJFileReader(path, configuration)) {
            Iterator<LineValue> iterator = fileReader.iterator();

            Assert.assertTrue("Deve retornar uma linha do arquivo.", iterator.hasNext());

            LineValue lineValue = iterator.next();
            Assert.assertEquals("1;2;19121991", lineValue.getContent());
            Assert.assertEquals(3, lineValue.getColumns().size());

            Assert.assertFalse("Não deve retornar porque o arquivo só tem uma linha.", iterator.hasNext());
        }
    }

    @Test
    public void mustParserFileContentToObject() throws IOException {
        Path path = createFileWithContent("1;descricao;1;1;100,37;93,42;19121991;justificativa");
        JFileReaderConfig configuration = JFileReaderFactory.newUtf8ReaderConfig("\\;");

        try (JFileReader fileReader = JFileReaderFactory.newJFileReader(path, configuration)) {
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
