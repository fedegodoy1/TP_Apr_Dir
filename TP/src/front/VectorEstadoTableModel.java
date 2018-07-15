package front;

import front.tablemodel.Columna;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import model.VectorEstadoConstants;
import model.VectorEstadoUI;

public class VectorEstadoTableModel extends DefaultTableModel implements VectorEstadoConstants {

    private List<VectorEstadoUI> datos;
    private List<Columna> columns;

    public VectorEstadoTableModel() {
        datos = new ArrayList();
        createColumnList();
    }

    @Override
    public int getRowCount() {
        return datos != null ? datos.size() : 0;
    }

    @Override
    public int getColumnCount() {
        return columns.size();
    }

    @Override
    public String getColumnName(int columnIndex) {
        return columns.get(columnIndex).getName();
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return columns.get(columnIndex).getTipo();
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    @Override
    public Object getValueAt(int row, int col) {
        Object value = null;
        switch (col) {
            case COL_RELOJ: {
                value = datos.get(row).getReloj();
                break;
            }
            case COL_EVENTO: {
                value = datos.get(row).getEvento().getNombre();
                break;
            }
            case COL_LLEGADA_ALUMNO_RND: {
                value = datos.get(row).getLlegadaVisitantes().getRnd();
                break;
            }
            case COL_LLEGADA_ALUMNO_TIEMPO: {
                value = datos.get(row).getLlegadaVisitantes().getTiempo_entre_llegadas();
                break;
            }
            case COL_LLEGADA_ALUMNO_PROXIMA: {
                value = datos.get(row).getLlegadaVisitantes().getProx_llegada();
                break;
            }
            case COL_LOTE: {
                value = datos.get(row).getAsignacionLote().getLoteVisitantes();
                break;
            }
            case COL_SALA_C_ESTADO: {
                value = datos.get(row).getSalas().get(0).getEstado();
                break;
            }
            case COL_SALA_C_CAPACIDAD: {
                value = datos.get(row).getSalas().get(0).getCapacidad();
                break;
            }
            case COL_SALA_C_COLA: {
                value = datos.get(row).getSalas().get(0).getCola();
                break;
            }
            case COL_SALA_A_ESTADO: {
                value = datos.get(row).getSalas().get(1).getEstado();
                break;
            }
            case COL_SALA_A_CAPACIDAD: {
                value = datos.get(row).getSalas().get(1).getCapacidad();
                break;
            }
            case COL_SALA_A_COLA: {
                value = datos.get(row).getSalas().get(1).getCola();
                break;
            }
            case COL_SALA_B_ESTADO: {
                value = datos.get(row).getSalas().get(2).getEstado();
                break;
            }
            case COL_SALA_B_CAPACIDAD: {
                value = datos.get(row).getSalas().get(2).getCapacidad();
                break;
            }
            case COL_SALA_B_COLA: {
                value = datos.get(row).getSalas().get(2).getCola();
                break;
            }
            case COL_SALA_D_ESTADO: {
                value = datos.get(row).getSalas().get(3).getEstado();
                break;
            }
            case COL_SALA_D_CAPACIDAD: {
                value = datos.get(row).getSalas().get(3).getCapacidad();
                break;
            }
            case COL_SALA_D_COLA: {
                value = datos.get(row).getSalas().get(3).getCola();
                break;
            }
            case COL_ACUM_VISITANTES: {
                value = datos.get(row).getAcumuladorVisitantes();
                break;
            }
            case COL_MAX_VISITANTES_EN_COLA: {
                value = datos.get(row).getMaxVisitantesEnEntrada();
            }
        }
        
        if (value instanceof Double)
        {
            Double val = (Double) value;
            if (val == Double.MAX_VALUE)
            {
                value = 0;
            }
            //value = value; //Acá. Ojo con reloj y eso.
        }
        return value;
    }

    private void createColumnList() {
        columns = new ArrayList();

        columns.add(new Columna("Reloj", Double.class, 75));
        columns.add(new Columna("Evento", String.class, 150));
        columns.add(new Columna("RND: Llegada visitantes", Double.class, 50));
        columns.add(new Columna("Tiempo: Llegada de visitantes", Double.class, 50));
        columns.add(new Columna("Proxima Llegada de visitantes", Double.class, 60));
        columns.add(new Columna("Lote de visitantes", Integer.class, 50));
        columns.add(new Columna("Estado Sala C", String.class, 100));
        columns.add(new Columna("Capacidad Sala C", Integer.class, 75));
        columns.add(new Columna("Cola Sala C", Integer.class, 50));
        columns.add(new Columna("Estado Sala A", String.class, 100));
        columns.add(new Columna("Capacidad Sala A", Integer.class, 75));
        columns.add(new Columna("Cola Sala A", Integer.class, 50));
        columns.add(new Columna("Estado Sala B", String.class, 100));
        columns.add(new Columna("Capacidad Sala B", Integer.class, 75));
        columns.add(new Columna("Cola Sala B", Integer.class, 50));
        columns.add(new Columna("Estado Sala D", String.class, 100));
        columns.add(new Columna("Capacidad Sala D", Integer.class, 75));
        columns.add(new Columna("Cola Sala D", Integer.class, 50));
        columns.add(new Columna("Acumulador de visitantes", Integer.class, 60));
        columns.add(new Columna("Maximo de visitantes en cola C", Integer.class, 75));
    }
    
    public void setDatos(List<VectorEstadoUI> modelo) {
        datos = modelo;
    }
    
    public void setColumnsWidth(TableColumnModel columnModel) {
        for (int i = 0; i < columnModel.getColumnCount(); i++)
        {
            columnModel.getColumn(i).setPreferredWidth(columns.get(i).getSize());
        }
    }
}
