package Contexts;

import java.util.List;
import Classes.Aankoop;

public interface IAankoopContext {
    List<Aankoop> HaalAlleAankopenOp();

    void VoegAankoopToe(Aankoop aankoop);
}
