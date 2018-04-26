package br.indie.fiscal4j.nfe400.webservices;

import br.indie.fiscal4j.DFUnidadeFederativa;
import br.indie.fiscal4j.nfe.NFeConfig;
import br.indie.fiscal4j.nfe400.classes.NFAutorizador400;
import br.indie.fiscal4j.nfe400.classes.cadastro.NFConsultaCadastro;
import br.indie.fiscal4j.nfe400.classes.cadastro.NFInfoConsultaCadastro;
import br.indie.fiscal4j.nfe400.classes.cadastro.NFRetornoConsultaCadastro;
import br.indie.fiscal4j.nfe400.webservices.consultacadastro.CadConsultaCadastro4Stub;
import br.indie.fiscal4j.nfe400.webservices.consultacadastro.CadConsultaCadastro4Stub.NfeDadosMsg;
import br.indie.fiscal4j.transformers.DFRegistryMatcher;
import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.util.AXIOMUtil;
import org.simpleframework.xml.core.Persister;
import org.simpleframework.xml.stream.Format;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.rmi.RemoteException;

class WSConsultaCadastro {
    private static final Logger LOG = LoggerFactory.getLogger(WSConsultaCadastro.class);
    private static final String NOME_SERVICO = "CONS-CAD";
    private static final String VERSAO_SERVICO = "2.00";
    private final NFeConfig config;

    WSConsultaCadastro(final NFeConfig config) {
        this.config = config;
    }

    NFRetornoConsultaCadastro consultaCadastro(final String cnpj, final DFUnidadeFederativa uf) throws Exception {
        final NFConsultaCadastro dadosConsulta = this.getDadosConsulta(cnpj, uf);
        final String xmlConsulta = dadosConsulta.toString();
        WSConsultaCadastro.LOG.debug(xmlConsulta);

        final OMElement omElementConsulta = AXIOMUtil.stringToOM(xmlConsulta);
        final OMElement resultado = this.efetuaConsulta(uf, omElementConsulta);

        final String retornoConsulta = resultado.toString();
        WSConsultaCadastro.LOG.debug(retornoConsulta);
        return new Persister(new DFRegistryMatcher(), new Format(0)).read(NFRetornoConsultaCadastro.class, retornoConsulta);
    }

    private OMElement efetuaConsulta(final DFUnidadeFederativa uf, final OMElement omElementConsulta) throws RemoteException {

        final NFAutorizador400 autorizador = NFAutorizador400.valueOfCodigoUF(uf);
        if (autorizador == null) {
            throw new IllegalStateException(String.format("UF %s nao possui autorizador para este servico", uf.getDescricao()));
        }
        final String url = autorizador.getConsultaCadastro(this.config.getAmbiente());
        WSConsultaCadastro.LOG.debug(String.format("Endpoint: %s", url));

        final NfeDadosMsg nfeDadosMsg0 = new NfeDadosMsg();
        nfeDadosMsg0.setExtraElement(omElementConsulta);

        return new CadConsultaCadastro4Stub(url).consultaCadastro(nfeDadosMsg0).getExtraElement();
    }

    private NFConsultaCadastro getDadosConsulta(final String cnpj, final DFUnidadeFederativa uf) {
        final NFConsultaCadastro consulta = new NFConsultaCadastro();
        consulta.setVersao(WSConsultaCadastro.VERSAO_SERVICO);
        consulta.setConsultaCadastro(new NFInfoConsultaCadastro());
        consulta.getConsultaCadastro().setCnpj(cnpj);
        consulta.getConsultaCadastro().setServico(WSConsultaCadastro.NOME_SERVICO);
        consulta.getConsultaCadastro().setUf(uf.getCodigo());
        return consulta;
    }
}