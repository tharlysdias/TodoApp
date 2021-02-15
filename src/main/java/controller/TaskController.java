package controller;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Task;
import util.ConnectionFactory;

/**
 *
 * @author tharlys
 */
public class TaskController {

    // Será criado 4 metodos, uma para cada operação basica que precisará ser
    // feita, vão receber a tarefa(Task) como parametro
    public void save(Task task) {

        // Criar uma String com o script SQL que precisa ser executado
        String sql = "INSERT INTO tasks (idProject, name, description, completed, notes, deadline, createdAt, updatedAt) VALUES (?,?,?,?,?,?,?,?)";

        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, task.getIdProject());
            statement.setString(2, task.getName());
            statement.setString(3, task.getDescription());
            statement.setBoolean(4, task.isIsCompleted());
            statement.setString(5, task.getNotes());
            statement.setDate(6, new Date(task.getDeadline().getTime()));
            statement.setDate(7, new Date(task.getCreatedAt().getTime()));
            statement.setDate(8, new Date(task.getUpdatedAt().getTime()));
            statement.execute();
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao salvar a tarefa " + ex.getMessage(), ex);
        } finally {
            ConnectionFactory.closeConnection(connection, statement);
        }
    }

    public void update(Task task) {

        String sql = "UPDATE tasks SET idProject = ?, name = ?, description = ?, completed = ?, notes = ?, deadline = ?, createdAt = ?, updatedAt = ? WHERE id = ?";

        Connection connection = null;
        PreparedStatement statement = null;

        try {
            // Estabelecendo a conexão com o banco de dados
            connection = ConnectionFactory.getConnection();

            // Preparando a query
            statement = connection.prepareStatement(sql);

            // Setando os valores do statement
            statement.setInt(1, task.getIdProject());
            statement.setString(2, task.getName());
            statement.setString(3, task.getDescription());
            statement.setBoolean(4, task.isIsCompleted());
            statement.setString(5, task.getNotes());
            statement.setDate(6, new Date(task.getDeadline().getTime()));
            statement.setDate(7, new Date(task.getCreatedAt().getTime()));
            statement.setDate(8, new Date(task.getUpdatedAt().getTime()));
            statement.setInt(9, task.getId());

            // Executando a query
            statement.execute();
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao atualizar a tarefa ", ex);
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException ex) {
                throw new RuntimeException("Erro ao fechar a conexão do update", ex);
            }
        }
    }

    public void removeById(int taskId) throws SQLException {

        String sql = "DELETE FROM tasks WHERE id = ?";

        Connection connection = null;
        PreparedStatement statement = null;

        try {
            // Criação da conexão com o banco de dados
            connection = ConnectionFactory.getConnection();

            // Preparando a query (comando para ser executado)
            statement = connection.prepareStatement(sql);

            // Setando os valores
            // Mudar a ?, recebendo o valor que for informado no id
            statement.setInt(1, taskId);

            statement.execute();
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao deletar a tarefa ", ex);
        } finally {
            // finally: Sempre é executado
            ConnectionFactory.closeConnection(connection, statement);
        }
    }

    // Esse List faz parte de um pacote de Estruturas de Dados que se tem dentro do java
    public List<Task> getAll(int idProject) {

        String sql = "SELECT * FROM tasks WHERE idProject = ?";

        Connection connection = null;
        PreparedStatement statement = null;
        // Quando se faz um SELECT no banco ele dá uma resposta, é necessário
        // ter uma classe (um objeto) que represente esse retorno do db
        // Guarda o que tivemos de resposta do banco de dados
        ResultSet resultSet = null;

        // Lista de tarefas que será devolvida quando a chamada do método acontecer
        // Estrutura de lista (Estrutura de dados)
        List<Task> tasks = new ArrayList<Task>();

        try {
            // Criação da conexão
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(sql);

            // Setando o valor que corresponde ao filtro de busca
            statement.setInt(1, idProject);

            // O executeQuery devolve o valor de resposta do SELECT feito no db
            // Valor retornado pela execução da query
            resultSet = statement.executeQuery();

            // Enquanto houverem valores a serem percorridos no meu resultSet
            while (resultSet.next()) {

                // Criar uma nova tarefa
                Task task = new Task();
                // setar o valor
                task.setId(resultSet.getInt("id"));
                task.setIdProject(resultSet.getInt("idProject"));
                task.setName(resultSet.getString("name"));
                task.setDescription(resultSet.getString("description"));
                task.setIsCompleted(resultSet.getBoolean("completed"));
                task.setNotes(resultSet.getString("notes"));
                task.setDeadline(resultSet.getDate("deadline"));
                task.setCreatedAt(resultSet.getDate("createdAt"));
                task.setUpdatedAt(resultSet.getDate("updatedAt"));

                // Colocar a lista dentro do tasks
                tasks.add(task);
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao inserir a tarefa ", ex);
        } finally {
            ConnectionFactory.closeConnection(connection, statement, resultSet);
        }

        // Lista de tarefas que foi criada e carregada do banco de dados
        return tasks;
    }

}
