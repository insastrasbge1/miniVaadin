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

import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import fr.insa.beuvron.web.amour.VuePrincipale;
import fr.insa.beuvron.web.amour.bdd.Aime;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author francois
 */
public class ListeDesUtilisateurs extends VerticalLayout {
    
    private VuePrincipale main;
    
    private UtilisateurGrid vgAllUsers;

    public ListeDesUtilisateurs(VuePrincipale main) {
        this.main = main;
        this.add(new H3("liste de tous les utilisateurs"));
        try {
            this.vgAllUsers = new UtilisateurGrid(
                    Aime.tousLesUtilisateurs(this.main.getSessionInfo().getConBdD()));
            this.add(this.vgAllUsers);
        } catch (SQLException ex) {
            this.add(new H3("Problème BdD : " + ex.getLocalizedMessage()));
        }
    }
}
