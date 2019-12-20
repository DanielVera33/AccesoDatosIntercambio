import java.util.HashMap;

import modelo.Zapatillas;

public interface InterfazPHP {
	public HashMap<Integer, Zapatillas> lee();
	public void anadirZapatillaJSON();
	public void updateZapatillaJSON();
}
