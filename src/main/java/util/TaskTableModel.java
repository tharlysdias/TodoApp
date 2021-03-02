package util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.Task;

/**
 *
 * @author tharlys
 */
public class TaskTableModel extends AbstractTableModel {

    String[] columns = {"Nome", "Descrição", "Prazo", "Tarefa Concluída", "Editar", "Excluir"}; // Colunas que vão aparecer na Table

    List<Task> tasks = new ArrayList<>(); // Lista dos dados que a table vai ter

    // Sobrescrita, pois estou sobrescrevendo um metodo que está na AbstractTableModel
    @Override
    // Metodo que diz quantas linhas (tarefas) eu vou ter
    public int getRowCount() {
        return tasks.size(); // retorna o número de tarefas que tenho
    }

    @Override
    // Metodo que diz quantas colunas eu tenho
    public int getColumnCount() {
        return columns.length; // retorna o número de colunas tem na table
    }

    @Override
    public String getColumnName(int columnIndex) {
        return columns[columnIndex];
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return columnIndex == 3;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        if(tasks.isEmpty()) { // Verificar se retorna vazio
            return Object.class;
        }
        return this.getValueAt(0, columnIndex).getClass();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) { // Metodo que vai dizer qual o valor deve ser exibido em cada linha e coluna
        
        switch (columnIndex) {
            case 0:
                return tasks.get(rowIndex).getName();
            case 1:
                return tasks.get(rowIndex).getDescription();
            case 2:
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/y"); // Formatando a data
                return dateFormat.format(tasks.get(rowIndex).getDeadline());
            case 3:
                return tasks.get(rowIndex).isIsCompleted();
            case 4:
                return "";
            case 5:
                return "";
            default:
                return "Dados não encontrados";
        }
    }
    
    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {  // Object é o valor ao qual foi setado no campo
        tasks.get(rowIndex).setIsCompleted((boolean) aValue); // quest: conversão de tipo, nesse caso de Object para boolean (orientação objeto)
    }

    public String[] getColumns() {
        return columns;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }
}
