package br.indie.fiscal4j.mdfe3.classes.nota;

import br.indie.fiscal4j.DFBase;
import br.indie.fiscal4j.validadores.DFListValidador;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

/**
 * Created by Eldevan Nery Junior on 01/11/17.
 * <h1>Informações de Vale Pedágio.</h1>
 * <p>
 * Outras informações sobre Vale-Pedágio obrigatório que não tenham campos específicos devem ser informadas no campo de observações gerais de uso livre pelo contribuinte, visando atender as determinações legais vigentes.
 * </p>
 */
@Root(name = "valePed")
public class MDFInfoModalRodoviarioPedagio extends DFBase {
    private static final long serialVersionUID = 3657414548123273405L;
    /**
     * Lista de dispositivos do Vale Pedágio.
     */
    @ElementList(entry = "disp", inline = true)
    private List<MDFInfoModalRodoviarioPedagioDisp> dispositivos;

    public List<MDFInfoModalRodoviarioPedagioDisp> getDispositivos() {
        return this.dispositivos;
    }

    public void setDispositivos(final List<MDFInfoModalRodoviarioPedagioDisp> dispositivos) {
        this.dispositivos = DFListValidador.validaListaObrigatoria(dispositivos, "Dispositivos do Vale Pedagio");
    }
}
