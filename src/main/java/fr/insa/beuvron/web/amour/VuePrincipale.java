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
package fr.insa.beuvron.web.amour;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import fr.insa.beuvron.web.amour.bdd.Aime;
import fr.insa.beuvron.web.amour.vues.BdDNonAccessible;
import fr.insa.beuvron.web.amour.vues.BienvenueMainVue;
import fr.insa.beuvron.web.amour.vues.InitialLoginEntete;
import fr.insa.beuvron.web.amour.vues.MyHorizontalLayout;
import fr.insa.beuvron.web.amour.vues.MyVerticalLayout;
import java.sql.SQLException;

/**
 * vue principale.
 * pour les composants vaadin, voir https://vaadin.com/docs/latest/ds/components
 * @author francois
 */
@Route(value = "")
@PageTitle("Amours")
public class VuePrincipale extends MyVerticalLayout {
    

    private SessionInfo sessionInfo;

    private MyHorizontalLayout entete;
    private MyVerticalLayout mainContent;

    public void setEntete(Component c) {
        this.entete.removeAll();
        this.entete.add(c);
    }

    public void setMainContent(Component c) {
        this.mainContent.removeAll();
        this.mainContent.add(c);
    }

    public VuePrincipale() {
        this.sessionInfo = new SessionInfo();
        this.entete = new MyHorizontalLayout();
        this.entete.setWidthFull();
        this.add(this.entete);

        this.mainContent = new MyVerticalLayout();
        this.mainContent.setWidthFull();
        this.mainContent.setHeightFull();
        this.add(this.mainContent);
        try {
            this.sessionInfo.setConBdD(Aime.defautConnect());
            this.setEntete(new InitialLoginEntete(this));
            this.setMainContent(new BienvenueMainVue(this));
        } catch (ClassNotFoundException | SQLException ex) {
            this.setMainContent(new BdDNonAccessible(this));
        }

    }

    
    /**
     * @return the sessionInfo
     */
    public SessionInfo getSessionInfo() {
        return sessionInfo;
    }

}
