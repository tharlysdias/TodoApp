package main;

import controller.ProjectController;
import controller.TaskController;
import java.sql.Connection;
import java.util.Date;
import java.util.List;
import model.Project;
import model.Task;
import util.ConnectionFactory;

/**
 *
 * @author tharlys
 */
// Gerenciador de dependencias - Gradle (responsavel nesse projeto)
// Criar as classes do modelo:
// 1 - Tarefa; 2 - Projeto; (Extras: 3 - Tags; 4 - User)
// Classes de modelo, quando se constroi uma aplicação em java, segue-se um
// padrão de projeto (padronização na construção)
// Padrão MVC = Model View Controler: Tem-se classes que representam os modelos
// (models), são que representam as entidades que temos dentro do sistema
// View: Interface Grafica (visualização), são as telas (classes de interface)
// Controler: Classes que controlam a aplicação, tanto a interação do banco com
// a tela, como também faz controles na aplicação (diversos)
// Existem outros padrões de projeto que podem ser aplicados
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

        // Cria uma nova tarefa no banco de dados
//        ProjectController projectController = new ProjectController();
//        Project project = new Project();
//        project.setName("Projeto teste");
//        project.setDescription("description");
//        projectController.save(project);

//        // Atualizar projeto já existente
//        ProjectController projectController = new ProjectController();
//        Project project = new Project();
//        project.setId(3);
//        project.setName("Novo nome do projeto");
//        project.setDescription("description");
//        projectController.update(project);
//
//        // Cria uma lista de projeto e faz a busca de todos os projetos
//        List<Project> projects = projectController.getAll();
//        System.out.println("Total de projetos = " + projects.size());

        // Remove um projeto do banco
//        projectController.removeById(1);


        // Cria uma nova tarefa
//        TaskController taskController = new TaskController();
//        
//        Task task = new Task();
//        task.setIdProject(3);
//        task.setName("Criar as telas da aplicação");
//        task.setDescription("Devem ser criadas telas para os cadastros");
//        task.setIsCompleted(false);
//        task.setNotes("Sem notas");
//        task.setDeadline(new Date());
//        taskController.save(task);


//        task.setName("Novas telas da aplicação");
//        taskController.update(task);
//        
//        List<Task> tasks = taskController.getAll(3);
//        System.out.println("Total de tarefas = " + tasks.size());
        
    }

}
