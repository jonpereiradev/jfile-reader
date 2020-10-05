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
package com.jonpereiradev.jfile.reader.model;


import com.jonpereiradev.jfile.reader.converter.DateTimeFormatter;
import com.jonpereiradev.jfile.reader.converter.DecimalFormatter;
import com.jonpereiradev.jfile.reader.converter.FileColumn;

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
