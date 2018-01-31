package br.indie.fiscal4j.mdfe3.classes.nota.consulta;

import br.indie.fiscal4j.DFAmbiente;
import br.indie.fiscal4j.DFBase;
import br.indie.fiscal4j.validadores.BigDecimalParser;
import br.indie.fiscal4j.validadores.StringValidador;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Root;

import java.math.BigDecimal;

@Root(name = "consSitMDFe")
@Namespace(reference = "http://www.portalfiscal.inf.br/mdfe")
public class MDFeNotaConsulta extends DFBase {

    @Attribute(name = "versao")
    private String versao;

    @Element(name = "tpAmb")
    private DFAmbiente ambiente;

    @Element(name = "xServ")
    private String servico;

    @Element(name = "chMDFe")
    private String chave;

    public String getVersao() {
        return this.versao;
    }

    public void setVersao(final BigDecimal versao) {
        this.versao = BigDecimalParser.tamanho4Com2CasasDecimais(versao, "Versao Nota Consulta");
    }

    public DFAmbiente getAmbiente() {
        return this.ambiente;
    }

    public void setAmbiente(final DFAmbiente ambiente) {
        this.ambiente = ambiente;
    }

    public String getServico() {
        return this.servico;
    }

    public void setServico(final String servico) {
        this.servico = servico;
    }

    public String getChave() {
        return this.chave;
    }

    public void setChave(final String chave) {
        StringValidador.exatamente44N(chave, "Chave de Acesso Nota Consulta");
        this.chave = chave;
    }
}