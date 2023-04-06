package br.indie.fiscal4j.nfe310.classes.nota;

import br.indie.fiscal4j.DFBase;
import br.indie.fiscal4j.nfe310.classes.NFNotaInfoSituacaoTributariaPIS;
import br.indie.fiscal4j.validadores.DFBigDecimalValidador;
import org.simpleframework.xml.Element;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

public class NFNotaInfoItemImpostoPISAliquota extends DFBase {
    private static final long serialVersionUID = 4109084296960847091L;

    private static final List<NFNotaInfoSituacaoTributariaPIS> SITUACOES_VALIDAS = Arrays.asList(NFNotaInfoSituacaoTributariaPIS.OPERACAO_TRIBUTAVEL_CUMULATIVO_NAO_CUMULATIVO, NFNotaInfoSituacaoTributariaPIS.OPERACAO_TRIBUTAVEL_ALIQUOTA_DIFERENCIADA);

    @Element(name = "CST")
    private NFNotaInfoSituacaoTributariaPIS situacaoTributaria;

    @Element(name = "vBC")
    private String valorBaseCalculo;

    @Element(name = "pPIS")
    private String percentualAliquota;

    @Element(name = "vPIS")
    private String valorTributo;

    public NFNotaInfoItemImpostoPISAliquota() {
        this.situacaoTributaria = null;
        this.valorBaseCalculo = null;
        this.percentualAliquota = null;
        this.valorTributo = null;
    }

    public void setSituacaoTributaria(final NFNotaInfoSituacaoTributariaPIS situacaoTributaria) {
        if (!NFNotaInfoItemImpostoPISAliquota.SITUACOES_VALIDAS.contains(situacaoTributaria)) {
            throw new IllegalStateException("Situacao tributaria invalida no item PIS aliquota");
        }
        this.situacaoTributaria = situacaoTributaria;
    }

    public void setValorBaseCalculo(final BigDecimal valorBaseCalculo) {
        this.valorBaseCalculo = DFBigDecimalValidador.tamanho15Com2CasasDecimais(valorBaseCalculo, "Valor BC PIS Item");
    }

    public void setPercentualAliquota(final BigDecimal aliquota) {
        this.percentualAliquota = DFBigDecimalValidador.tamanho7ComAte4CasasDecimais(aliquota, "Aliquota PIS Item");
    }

    public void setValorTributo(final BigDecimal valor) {
        this.valorTributo = DFBigDecimalValidador.tamanho15Com2CasasDecimais(valor, "Valor PIS Item");
    }

    public NFNotaInfoSituacaoTributariaPIS getSituacaoTributaria() {
        return this.situacaoTributaria;
    }

    public String getValorBaseCalculo() {
        return this.valorBaseCalculo;
    }

    public String getPercentualAliquota() {
        return this.percentualAliquota;
    }

    public String getValorTributo() {
        return this.valorTributo;
    }
}