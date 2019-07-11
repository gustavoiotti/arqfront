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


@Controller
public class CursoController {



    @GetMapping("/curso")
    public String inicial(Model data) throws JsonSyntaxException, UnirestException {

        CursoModel arrayCursos[] = new Gson()
                .fromJson(
                        Unirest
                                .get("http://localhost:9000/servico/curso")
                                .asJson()
                                .getBody()
                                .toString(),
                        CursoModel[].class
                );

        data.addAttribute("cursos", arrayCursos);

        return "curso-view";
    }

    @PostMapping ("/curso/criar")
    public String criar(CursoModel curso) throws UnirestException {

        Unirest.post("http://localhost:9000/servico/curso")
                .header("Content-type", "application/json")
                .header("accept", "application/json")
                .body(new Gson().toJson(curso, CursoModel.class))
                .asJson();

        return "redirect:/curso";
    }

    @GetMapping ("/curso/excluir")
    public String excluir (@RequestParam int id) throws UnirestException {

        Unirest
                .delete("http://localhost:9000/servico/curso/{id}")
                .routeParam("id", String.valueOf(id))
                .asJson();

        return "redirect:/curso";
    }

    @GetMapping ("/curso/prepara-alterar")
    public String preparaAlterar (@RequestParam int id, Model data) throws JsonSyntaxException, UnirestException {

        CursoModel cursoExistente = new Gson()
                .fromJson(
                        Unirest
                                .get("http://localhost:9000/servico/curso/{id}")
                                .routeParam("id", String.valueOf(id))
                                .asJson()
                                .getBody()
                                .toString(),
                        CursoModel.class
                );

        data.addAttribute("cursoAtual", cursoExistente);

        CursoModel arrayCursos[] = new Gson()
                .fromJson(
                        Unirest
                                .get("http://localhost:9000/servico/curso")
                                .asJson()
                                .getBody()
                                .toString(),
                            CursoModel[].class
                );

        data.addAttribute("cursos", arrayCursos);

        return "curso-view-alterar";
    }

    @PostMapping ("/curso/alterar")
    public String alterar (CursoModel cursoAlterado) throws UnirestException {

        Unirest
                .put("http://localhost:9000/servico/curso/{id}")
                .routeParam("id", String.valueOf(cursoAlterado.getId()))
                .header("Content-type", "application/json")
                .header("accept", "application/json")
                .body(new Gson().toJson(cursoAlterado, CursoModel.class))
                .asJson();

        return "redirect:/curso";
    }
}


