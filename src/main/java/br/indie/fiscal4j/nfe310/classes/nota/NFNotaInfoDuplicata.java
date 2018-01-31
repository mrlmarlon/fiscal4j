package br.indie.fiscal4j.nfe310.classes.nota;

import br.indie.fiscal4j.DFBase;
import br.indie.fiscal4j.validadores.BigDecimalParser;
import br.indie.fiscal4j.validadores.StringValidador;
import org.joda.time.LocalDate;
import org.simpleframework.xml.Element;

import java.math.BigDecimal;

public class NFNotaInfoDuplicata extends DFBase {
    private static final long serialVersionUID = 4401957395684813604L;

    @Element(name = "nDup", required = false)
    private String numeroDuplicata;

    @Element(name = "dVenc", required = false)
    private LocalDate dataVencimento;

    @Element(name = "vDup", required = true)
    private String valorDuplicata;

    public void setValorDuplicata(final BigDecimal valorDuplicata) {
        this.valorDuplicata = BigDecimalParser.tamanho15Com2CasasDecimais(valorDuplicata, "Valor Duplicata");
    }

    public String getValorDuplicata() {
        return this.valorDuplicata;
    }

    public void setValorDuplicata(final String valorDuplicata) {
        this.valorDuplicata = valorDuplicata;
    }

    public String getNumeroDuplicata() {
        return this.numeroDuplicata;
    }

    public void setNumeroDuplicata(final String numeroDuplicata) {
        StringValidador.tamanho60(numeroDuplicata, "Numero Duplicata");
        this.numeroDuplicata = numeroDuplicata;
    }

    public LocalDate getDataVencimento() {
        return this.dataVencimento;
    }

    public void setDataVencimento(final LocalDate dataVencimento) {
        this.dataVencimento = dataVencimento;
    }
}