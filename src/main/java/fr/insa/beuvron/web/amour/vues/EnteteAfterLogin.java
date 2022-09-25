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
import fr.insa.beuvron.web.amour.VuePrincipale;
import java.util.Optional;

/**
 *
 * @author francois
 */
public class EnteteAfterLogin extends MyHorizontalLayout {
    private VuePrincipale main;
    
    private Button vbLogout;
    private Button vbListeDesUtilisateurs;
    private Button vbGestionAmours;
    
    public EnteteAfterLogin(VuePrincipale main) {
        this.main = main;
        
        this.vbLogout = new Button("logout");
        this.vbLogout.addClickListener((event) -> {
            this.doLogout();
        });
        this.vbListeDesUtilisateurs = new Button("liste des utilisateurs");
        this.vbListeDesUtilisateurs.addClickListener((event) -> {
            this.main.setMainContent(new ListeDesUtilisateurs(this.main));
        });
        this.vbGestionAmours = new Button("Gérer vos amours");
        this.vbGestionAmours.addClickListener((event) -> {
            this.main.setMainContent(new MainAfterLogin(this.main));
        });
        this.add(this.vbLogout,this.vbListeDesUtilisateurs,this.vbGestionAmours);
    }
    
    public void doLogout() {
        this.main.getSessionInfo().setCurUser(Optional.empty());
        this.main.setEntete(new InitialLoginEntete(this.main));
        this.main.setMainContent(new BienvenueMainVue(this.main));
    }
    
}
