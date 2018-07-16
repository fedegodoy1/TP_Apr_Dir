package front;

import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import objects.Visitantes;

public class PopUpVisitantes extends javax.swing.JFrame {

    private List<Visitantes> visitantes;
    private JTable tabla;
    private VisitantesTableModel tableModel;

    public PopUpVisitantes() {
        initComponents();
        setearTabla();
    }
    
    public PopUpVisitantes(List<Visitantes> visit) {
        visitantes = visit;
        initComponents();
        setearTabla();
        
    }
    
    void setVisitantes(List<Visitantes> visit) {
        this.visitantes = visit;
        tableModel.fireTableDataChanged();
    }
    
    private void setearTabla() {
        tabla = new JTable();
        jsp_visitantes.setViewportView(tabla);
        tableModel = new VisitantesTableModel();
        tabla.setModel(tableModel);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jsp_visitantes = new javax.swing.JScrollPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jsp_visitantes, javax.swing.GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jsp_visitantes, javax.swing.GroupLayout.DEFAULT_SIZE, 278, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jsp_visitantes;
    // End of variables declaration//GEN-END:variables

    private class VisitantesTableModel extends DefaultTableModel {

        @Override
        public Object getValueAt(int row, int col) {
            Object value = null;
            if (row >= 0 && visitantes != null) {
                Visitantes visitante = visitantes.get(row);
                switch (col) {
                    case 0: {
                        value = visitante.getAsignacion().getRnd();
                        break;
                    }
                    case 1: {
                        value = visitante.getAsignacion().getSalas();
                        break;
                    }
                    case 2: {
                        value = visitante.getFinRecorridoC().getRnd();
                        break;
                    }
                    case 3: {
                        value = visitante.getFinRecorridoC().gettRecorrido();
                        break;
                    }
                    case 4: {
                        value = visitante.getFinRecorridoC().getFinRecorrido();
                        break;
                    }
                    case 5: {
                        value = visitante.getFinRecorridoA().getRnd1();
                        break;
                    }
                    case 6: {
                        value = visitante.getFinRecorridoA().getRnd2();
                        break;
                    }
                    case 7: {
                        value = visitante.getFinRecorridoA().gettRecorrido();
                        break;
                    }
                    case 8: {
                        value = visitante.getFinRecorridoA().getFinRecorrido();
                        break;
                    }
                    case 9: {
                        value = visitante.getFinRecorridoB().getRnd1();
                        break;
                    }
                    case 10: {
                        value = visitante.getFinRecorridoB().getRnd2();
                        break;
                    }
                    case 11: {
                        value = visitante.getFinRecorridoB().gettRecorrido();
                        break;
                    }
                    case 12: {
                        value = visitante.getFinRecorridoB().getFinRecorrido();
                        break;
                    }
                    case 13: {
                        value = visitante.getFinRecorridoD().getRnd();
                        break;
                    }
                    case 14: {
                        value = visitante.getFinRecorridoD().gettRecorrido();
                        break;
                    }
                    case 15: {
                        value = visitante.getFinRecorridoD().getFinRecorrido();
                        break;
                    }
                    case 16: {
                        value = visitante.getEstado();
                        break;
                    }
                    case 17: {
                        value = visitante.getSala().getNombre();
                        break;
                    }
                }
            }
            return value;
        }

        @Override
        public int getRowCount() {
            return visitantes != null ? visitantes.size() : 0;
        }

        @Override
        public int getColumnCount() {
            return 18;
        }

        @Override
        public String getColumnName(int columnIndex) {
            String colName = "";
            switch (columnIndex) {
                case 0: {
                    colName = "RND asignacion de recorrido";
                    break;
                }
                case 1: {
                    colName = "Recorrido a tomar";
                    break;
                }
                case 2: {
                    colName = "RND: tiempo del recorrido Sala C";
                    break;
                }
                case 3: {
                    colName = "Tiempo de recorrido Sala C";
                    break;
                }
                case 4: {
                    colName = "Fin de recorrido Sala C";
                    break;
                }
                case 5: {
                    colName = "RND 1: tiempo del recorrido Sala A";
                    break;
                }
                case 6: {
                    colName = "RND 2: tiempo del recorrido Sala A";
                    break;
                }
                case 7: {
                    colName = "Tiempo de recorrido Sala A";
                    break;
                }
                case 8: {
                    colName = "Fin de recorrido Sala A";
                    break;
                }
                case 9: {
                    colName = "RND 1: tiempo del recorrido Sala B";
                    break;
                }
                case 10: {
                    colName = "RND 2: tiempo del recorrido Sala B";
                    break;
                }
                case 11: {
                    colName = "Tiempo de recorrido Sala B";
                    break;
                }
                case 12: {
                    colName = "Fin de recorrido Sala B";
                    break;
                }
                case 13: {
                    colName = "RND: tiempo del recorrido Sala D";
                    break;
                }
                case 14: {
                    colName = "Tiempo de recorrido Sala D";
                    break;
                }
                case 15: {
                    colName = "Fin de recorrido Sala D";
                    break;
                }
                case 16: {
                    colName = "Estado visitante";
                    break;
                }
                case 17: {
                    colName = "Sala en que se encuentra el visitante";
                    break;
                }
            }
            return colName;
        }
        
        @Override
        public Class<?> getColumnClass(int columnIndex) {
            switch(columnIndex) {
                case 0: {
                    return Double.class;
                }
                case 1: {
                    return String.class;
                }
                case 2: {
                    return Double.class;
                }
                case 3: {
                    return Double.class;
                }
                case 4: {
                    return Double.class;
                }
                case 5: {
                    return Double.class;
                }
                case 6: {
                    return Double.class;
                }
                case 7: {
                    return Double.class;
                }
                case 8: {
                    return Double.class;
                }
                case 9: {
                    return Double.class;
                }
                case 10: {
                    return Double.class;
                }
                case 11: {
                    return Double.class;
                }
                case 12: {
                    return Double.class;
                }
                case 13: {
                    return Double.class;
                }
                case 14: {
                    return Double.class;
                }
                case 15: {
                    return Double.class;
                }
                case 16: {
                    return String.class;
                }
                case 17: {
                    return String.class;
                }
            }
            return Class.class;
        }
        
        @Override
        public boolean isCellEditable(int rowIndex, int columnIndex) {
            return false;
        }
    }
}
