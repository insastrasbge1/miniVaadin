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

import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import fr.insa.beuvron.web.amour.VuePrincipale;

/**
 *
 * @author francois
 */
public class MainAfterLogin extends MyVerticalLayout {

    private VuePrincipale main;

    private Tab aimeCurrentUser;
    private Tab changeAime;
    private Tabs allTabs;

    private MyVerticalLayout tabContent;

    public MainAfterLogin(VuePrincipale main) {
        this.main = main;

        this.aimeCurrentUser = new Tab("Amours de "
                + this.main.getSessionInfo().getUserName());

        this.changeAime = new Tab("modifier");
        this.allTabs = new Tabs(this.aimeCurrentUser, this.changeAime);
        this.allTabs.addSelectedChangeListener(event
                -> setTabContent(event.getSelectedTab())
        );
        this.tabContent = new MyVerticalLayout();
        this.tabContent.setHeightFull();
        this.tabContent.setWidthFull();
        this.tabContent.getStyle().set("border", "1px solid black");
        this.add(this.allTabs, this.tabContent);
        this.allTabs.setSelectedTab(this.aimeCurrentUser);
        this.tabContent.add(new PanneauShowAime(this.main));
    }

    public void setTabContent(Tab curTab) {
        this.tabContent.removeAll();
        if (curTab == this.aimeCurrentUser) {
            this.tabContent.add(new PanneauShowAime(this.main));
        } else if (curTab == this.changeAime) {
            this.tabContent.add(new PanneauModifyAime(this.main));
        }

    }
}
