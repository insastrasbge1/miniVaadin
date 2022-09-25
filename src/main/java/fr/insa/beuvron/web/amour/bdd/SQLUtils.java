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
package fr.insa.beuvron.web.amour.bdd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author francois
 */
public class SQLUtils {

    public static class ResultSetAsList {

        private final List<String> entetes;
        private final List<List<Object>> valeurs;

        public ResultSetAsList(List<String> entetes, List<List<Object>> valeurs) {
            this.entetes = entetes;
            this.valeurs = valeurs;
        }

    }

    public static ResultSetAsList asLists(Connection con, PreparedStatement pst) throws SQLException {
        try ( ResultSet rs = pst.executeQuery()) {
            ResultSetMetaData meta = rs.getMetaData();
            List<String> entetes = new ArrayList<>(meta.getColumnCount());
            for (int c = 1; c <= meta.getColumnCount(); c++) {
                entetes.add(meta.getColumnName(c));
            }
            List<List<Object>> datas = new ArrayList<>(meta.getColumnCount());
            while (rs.next()) {
                List<Object> ligne = new ArrayList<>();
                for (int c = 1; c <= meta.getColumnCount(); c++) {
                    ligne.add(rs.getObject(c));
                }
                datas.add(ligne);
            }
            return new ResultSetAsList(entetes, datas);
        }
    }

}
