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
import com.vaadin.flow.component.textfield.TextField;
import fr.insa.beuvron.web.amour.VuePrincipale;
import fr.insa.beuvron.web.amour.bdd.Aime;
import java.sql.SQLException;

/**
 *
 * @author francois
 */
public class DefConnectionBDD extends MyVerticalLayout {

    private VuePrincipale main;
    private TextField vtHost;
    private TextField vtPort;
    private TextField vtDatabase;
    private TextField vtUser;
    private TextField vtPass;
    private Button vbRetryConnect;

    public DefConnectionBDD(VuePrincipale main) {
        this.main = main;

        Paragraph note = new Paragraph("Note : normalement ce panneau ne devrait pas faire partie "
                + "de l'interface web. Il est inclu ici pour s'adapter au diverses "
                + "configurations des ordinateurs des étudiants");
        note.getStyle().set("font-family", "Monospace")
                .set("font-size", "0.8em").set("color", "red")
                .set("font-style", "italic");
        this.add(note);
        this.getStyle().set("border", "2px solid " + "red");

        this.vtHost = new TextField("host");
        this.vtHost.setValue("localhost");
        this.vtPort = new TextField("port");
        this.vtPort.setValue("5439");
        this.vtDatabase = new TextField("database");
        this.vtDatabase.setValue("postgres");
        this.vtUser = new TextField("user");
        this.vtUser.setValue("postgres");
        this.vtPass = new TextField("password");
        this.vtPass.setValue("pass");
        this.vbRetryConnect = new Button("retenter de se connecter");
        this.vbRetryConnect.addClickListener((event) -> {
            try {
                this.main.getSessionInfo().setConBdD(
                        Aime.connectGeneralPostGres(
                                this.vtHost.getValue(),
                                Integer.parseInt(this.vtPort.getValue()),
                                this.vtDatabase.getValue(),
                                this.vtUser.getValue(),
                                this.vtPass.getValue()));
                this.main.setEntete(new InitialLoginEntete(this.main));
                this.main.setMainContent(new BienvenueMainVue(this.main));

            } catch (ClassNotFoundException | SQLException ex) {
                Notification.show("Problème : " + ex.getLocalizedMessage());
            }
        });
        this.add(vtHost, vtPort, vtDatabase, vtUser, vtPass, vbRetryConnect);
    }
}
