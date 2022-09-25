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

import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Paragraph;
import fr.insa.beuvron.web.amour.VuePrincipale;

/**
 *
 * @author francois
 */
public class BienvenueMainVue extends MyVerticalLayout{
    
    private VuePrincipale main;
    
    public BienvenueMainVue(VuePrincipale main) {
        this.main = main;
        this.add(new H1("bienvenu dans ce super programme"));
        this.add(new Paragraph("merci de vous connecter"));
        if (ConfigGenerale.AFFICHE_RAZ_DATABASE) {
            this.add(new InitOrResetDatabase(this.main));
        }
    }
    
}
