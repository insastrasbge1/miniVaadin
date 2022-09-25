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
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.notification.Notification;
import fr.insa.beuvron.web.amour.VuePrincipale;
import fr.insa.beuvron.web.amour.bdd.Aime;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author francois
 */
public class InitOrResetDatabase extends MyVerticalLayout {

    private VuePrincipale main;
    
    private Button vbInitDatabase;

    public InitOrResetDatabase(VuePrincipale main) {
        this.main = main;

        Paragraph note = new Paragraph("Note : normalement ce panneau ne devrait pas faire partie "
                + "de l'interface web : la création de la base de donnée se fait une fois pour "
                + "toute par l'administrateur système, en général hors de l'interface web");
        note.getStyle().set("font-family", "Monospace")
                .set("font-size", "0.8em").set("color", "red")
                .set("font-style", "italic");
        this.add(note);
        this.getStyle().set("border", "2px solid " + "red");
        
        this.vbInitDatabase = new Button("Initialisation ou ré-initialisation de la base de donnée");
        this.vbInitDatabase.addClickListener((event) -> {
            Connection con = this.main.getSessionInfo().getConBdD();
            try {
                Aime.recreeTout(con);
                Notification.show("OK : BdD (ré)-initialisée");
            } catch (SQLException ex) {
                Notification.show("Problème : "+ex.getLocalizedMessage());
            }
        });
        this.add(this.vbInitDatabase);

    }
}
