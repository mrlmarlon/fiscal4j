package br.indie.fiscal4j.nfe.transformers;

import br.indie.fiscal4j.nfe.classes.NFFinalidade;
import org.simpleframework.xml.transform.Transform;

class NFFinalidadeTransformer implements Transform<NFFinalidade> {

    @Override
    public NFFinalidade read(final String codigo) throws Exception {
        return NFFinalidade.valueOfCodigo(codigo);
    }

    @Override
    public String write(final NFFinalidade tipo) throws Exception {
        return tipo.getCodigo();
    }
}