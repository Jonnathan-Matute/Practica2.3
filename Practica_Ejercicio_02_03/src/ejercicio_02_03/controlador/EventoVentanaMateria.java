/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicio_02_03.controlador;

import ejercicio_02_03.modelo.Materia;
import ejercicio_02_03.vista.VentanaMateria;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 *
 * @author jonnathan matute
 */
public class EventoVentanaMateria implements ActionListener {

    private final VentanaMateria vMateria;

    public EventoVentanaMateria(VentanaMateria vMateria) {
        this.vMateria = vMateria;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(this.vMateria.getbGuardar())) {

            String n = this.vMateria.getTxtList().get(0).getText();
            String n2 = this.vMateria.getTxtList().get(1).getText();
            int c = this.vMateria.getComboBox().getSelectedIndex();

            if (n.isEmpty() && n2.isEmpty()) {
                JOptionPane.showMessageDialog(vMateria, "", "", JOptionPane.INFORMATION_MESSAGE);
            } else {
                int cont = 0;
                boolean cent = true;
                for (Materia m : this.vMateria.getGd().getMateriaList()) {
                    if (n.compareTo(this.vMateria.getGd().getMateriaList().get(cont).getNombre()) == 0) {
                        JOptionPane.showMessageDialog(vMateria, "Ya se encuentra esta materia en nuestra base de datos", "Parámetro Repetido", JOptionPane.ERROR_MESSAGE);
                        cent = false;
                        break;
                    } else {
                        cent = true;
                    }
                    cont++;
                }
                if (cent == true) {
                    this.vMateria.getGd().addMateria(new Materia(n,n2,this.vMateria.getGd().getGrupoList().get(cont)));
                    this.vMateria.getModeloTabla().setDataVector(this.cargaMateria(this.vMateria.getGd().getMateriaList().size(), 3), this.vMateria.getEncabezado());
                }
            }
            this.vMateria.getTxtList().get(0).setText("");
            this.vMateria.getTxtList().get(1).setText("");
            this.vMateria.getComboBox().setSelectedItem(-1);

      }

    }

    public Object[][] cargaMateria(int f, int c) {
        Object[][] retorno = new Object[f][c];
        int i = 0;
        for (Materia ma: this.vMateria.getGd().getMateriaList()) {
            retorno[i][0] = ma.getNombre();
            retorno[i][1] = ma.getNomDocente();
            retorno[i][2] = ma.getNomGrupo().getNombre();
            i++;
        }
        return retorno;
    }

}
