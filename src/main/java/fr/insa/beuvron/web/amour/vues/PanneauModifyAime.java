/*
    Copyright 2000- Francois de Bertrand de Beuvron

    This file is part of CoursBeuvron.

    CoursBeuvron is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    CoursBeuvron is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with CoursBeuvron.  If not, see <http://www.gnu.org/licenses/>.
 */
package fr.insa.beuvron.web.amour.vues;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.notification.Notification;
import fr.insa.beuvron.web.amour.VuePrincipale;
import fr.insa.beuvron.web.amour.bdd.Aime;
import fr.insa.beuvron.web.amour.model.Utilisateur;
import java.sql.SQLException;
import java.util.List;
import java.util.Set;

/**
 *
 * @author francois
 */
public class PanneauModifyAime extends MyVerticalLayout {

    private VuePrincipale main;

    private UtilisateurSelectGrid vaime;
    private UtilisateurSelectGrid vpasAime;
    private Button vbValidate;
    private Button vbCancel;
    private Button vbAdd;
    private Button vbRemove;

    public PanneauModifyAime(VuePrincipale main) {
        this.main = main;
        this.reInit();
    }
    
    private void reInit() {
        this.removeAll();
        this.add(new Paragraph("Note : normalement, on n'affiche pas les ids"));
        this.vbValidate = new Button("Valider les modifs.");
        this.vbValidate.addClickListener((event) -> {
            try {
                Aime.changeAllAimes(this.main.getSessionInfo().getConBdD(),
                        this.main.getSessionInfo().getCurUser().orElseThrow(),
                        this.vaime.getDatas());
                Notification.show("Modifications validées");
            } catch (SQLException ex) {
                Notification.show("Problem BdD : " + ex.getLocalizedMessage());
            }
        });
        this.vbCancel = new Button("Annuler les dernières modifs.");
        this.vbCancel.addClickListener((event) -> {
            this.reInit();
        });
        MyHorizontalLayout hlButtons = new MyHorizontalLayout(this.vbValidate, this.vbCancel);
        this.add(hlButtons);
 
        MyHorizontalLayout hlAime = new MyHorizontalLayout();
        hlAime.setWidthFull();
        MyVerticalLayout vlPasAimes = new MyVerticalLayout();
        vlPasAimes.add(new H3("vous n'aimez pas"));
        try {
            List<Utilisateur> datas = Aime.quiNeSontPasAimesPar(
                    this.main.getSessionInfo().getConBdD(), this.main.getSessionInfo().getCurUser().orElseThrow());
            this.vpasAime = new UtilisateurSelectGrid(datas);
            vlPasAimes.add(this.vpasAime);
        } catch (SQLException ex) {
            vlPasAimes.add(new H1("Probleme BdD"));
        }
        hlAime.add(vlPasAimes);
        this.vbAdd = new Button("ADD >>");
        this.vbAdd.addClickListener((event) -> {
            Set<Utilisateur> select = this.vpasAime.getSelectedItems();
            this.vaime.addUtilisateurs(select);
            this.vpasAime.removeUtilisateurs(select);
        });
        this.vbRemove = new Button("<< REMOVE");
        this.vbRemove.addClickListener((event) -> {
            Set<Utilisateur> select = this.vaime.getSelectedItems();
            this.vpasAime.addUtilisateurs(select);
            this.vaime.removeUtilisateurs(select);

        });
        MyVerticalLayout vbuttons = new MyVerticalLayout(this.vbAdd, this.vbRemove);
        hlAime.add(vbuttons);

        MyVerticalLayout vlAime = new MyVerticalLayout();
        vlAime.add(new H3("vous aimez"));
        try {
            List<Utilisateur> datas = Aime.quiSontAimesPar(
                    this.main.getSessionInfo().getConBdD(), this.main.getSessionInfo().getCurUser().orElseThrow());
            this.vaime = new UtilisateurSelectGrid(datas);
            vlAime.add(this.vaime);
        } catch (SQLException ex) {
            vlAime.add(new H1("Probleme BdD"));
        }
        hlAime.add(vlAime);
        this.add(hlAime);
    }

}
