package com.jonpereiradev.jfile.reader.model;

import com.jonpereiradev.jfile.reader.parser.DateTimeFormatter;
import com.jonpereiradev.jfile.reader.parser.DecimalFormatter;
import com.jonpereiradev.jfile.reader.parser.FileColumn;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Example {

    @FileColumn(1)
    private Short tipoDispendio;

    @FileColumn(2)
    private String descricao;

    @FileColumn(3)
    private Short tipoApropriacao;

    @FileColumn(4)
    private Short outroTipoApropriacao;

    @FileColumn(5)
    @DecimalFormatter("#,##0.0#")
    private BigDecimal valorDispendio;

    @FileColumn(6)
    @DecimalFormatter("#,##0.0#")
    private BigDecimal valorDispendioDepreciacao;

    @FileColumn(7)
    @DateTimeFormatter("ddMMyyyy")
    private LocalDate dataAquisicao;

    @FileColumn(8)
    private String justificativa;

    public Short getTipoDispendio() {
        return tipoDispendio;
    }

    public void setTipoDispendio(Short tipoDispendio) {
        this.tipoDispendio = tipoDispendio;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Short getTipoApropriacao() {
        return tipoApropriacao;
    }

    public void setTipoApropriacao(Short tipoApropriacao) {
        this.tipoApropriacao = tipoApropriacao;
    }

    public Short getOutroTipoApropriacao() {
        return outroTipoApropriacao;
    }

    public void setOutroTipoApropriacao(Short outroTipoApropriacao) {
        this.outroTipoApropriacao = outroTipoApropriacao;
    }

    public BigDecimal getValorDispendio() {
        return valorDispendio;
    }

    public void setValorDispendio(BigDecimal valorDispendio) {
        this.valorDispendio = valorDispendio;
    }

    public BigDecimal getValorDispendioDepreciacao() {
        return valorDispendioDepreciacao;
    }

    public void setValorDispendioDepreciacao(BigDecimal valorDispendioDepreciacao) {
        this.valorDispendioDepreciacao = valorDispendioDepreciacao;
    }

    public LocalDate getDataAquisicao() {
        return dataAquisicao;
    }

    public void setDataAquisicao(LocalDate dataAquisicao) {
        this.dataAquisicao = dataAquisicao;
    }

    public String getJustificativa() {
        return justificativa;
    }

    public void setJustificativa(String justificativa) {
        this.justificativa = justificativa;
    }
}
