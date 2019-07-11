package br.edu.utfpr.apresentacao;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

public class DisciplinaController {
    @GetMapping("/disciplina")
    public String inicial(Model data) throws JsonSyntaxException, UnirestException {

        DisciplinaModel arrayDisciplinas[] = new Gson()
                .fromJson(
                        Unirest
                                .get("http://localhost:9000/servico/disciplina")
                                .asJson()
                                .getBody()
                                .toString(),
                                DisciplinaModel[].class
                );

        data.addAttribute("disciplinas", arrayDisciplinas);

        return "disciplina-view";
    }

    @PostMapping ("/disciplina/criar")
    public String criar(DisciplinaModel disciplina) throws UnirestException {

        Unirest.post("http://localhost:9000/servico/disciplina")
                .header("Content-type", "application/json")
                .header("accept", "application/json")
                .body(new Gson().toJson(disciplina, DisciplinaModel.class))
                .asJson();

        return "redirect:/disciplina";
    }

    @GetMapping ("/disciplina/excluir")
    public String excluir (@RequestParam int id) throws UnirestException {

        Unirest
                .delete("http://localhost:9000/servico/disciplina/{id}")
                .routeParam("id", String.valueOf(id))
                .asJson();

        return "redirect:/disciplina";
    }

    @GetMapping ("/disciplina/prepara-alterar")
    public String preparaAlterar (@RequestParam int id, Model data) throws JsonSyntaxException, UnirestException {

        DisciplinaModel disciplinaExistente = new Gson()
                .fromJson(
                        Unirest
                                .get("http://localhost:9000/servico/disciplina/{id}")
                                .routeParam("id", String.valueOf(id))
                                .asJson()
                                .getBody()
                                .toString(),
                                DisciplinaModel.class
                );

        data.addAttribute("disciplinaAtual", disciplinaExistente);

        DisciplinaModel arrayDisciplinas[] = new Gson()
                .fromJson(
                        Unirest
                                .get("http://localhost:9000/servico/disciplina")
                                .asJson()
                                .getBody()
                                .toString(),
                                DisciplinaModel[].class
                );

        data.addAttribute("disciplinas", arrayDisciplinas);

        return "disciplina-view-alterar";
    }

    @PostMapping ("/disciplina/alterar")
    public String alterar (DisciplinaModel disciplinaAlterado) throws UnirestException {

        Unirest
                .put("http://localhost:9000/servico/disciplina/{id}")
                .routeParam("id", String.valueOf(disciplinaAlterado.getId()))
                .header("Content-type", "application/json")
                .header("accept", "application/json")
                .body(new Gson().toJson(disciplinaAlterado, DisciplinaModel.class))
                .asJson();

        return "redirect:/disciplina";
    }
}
