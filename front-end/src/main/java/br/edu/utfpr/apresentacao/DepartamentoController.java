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

public class DepartamentoController {
    
    @GetMapping("/departamento")
    public String inicial(Model data) throws JsonSyntaxException, UnirestException {

        DepartamentoModel arrayDepartamentos[] = new Gson()
                .fromJson(
                        Unirest
                                .get("http://localhost:9000/servico/departamento")
                                .asJson()
                                .getBody()
                                .toString(),
                            DepartamentoModel[].class
                );

        data.addAttribute("departamentos", arrayDepartamentos);

        return "departamento-view";
    }

    @PostMapping ("/departamento/criar")
    public String criar(DepartamentoModel departamento) throws UnirestException {

        Unirest.post("http://localhost:9000/servico/departamento")
                .header("Content-type", "application/json")
                .header("accept", "application/json")
                .body(new Gson().toJson(departamento, DepartamentoModel.class))
                .asJson();

        return "redirect:/departamento";
    }

    @GetMapping ("/departamento/excluir")
    public String excluir (@RequestParam int id) throws UnirestException {

        Unirest
                .delete("http://localhost:9000/servico/departamento/{id}")
                .routeParam("id", String.valueOf(id))
                .asJson();

        return "redirect:/departamento";
    }

    @GetMapping ("/departamento/prepara-alterar")
    public String preparaAlterar (@RequestParam int id, Model data) throws JsonSyntaxException, UnirestException {

        DepartamentoModel departamentoExistente = new Gson()
                .fromJson(
                        Unirest
                                .get("http://localhost:9000/servico/departamento/{id}")
                                .routeParam("id", String.valueOf(id))
                                .asJson()
                                .getBody()
                                .toString(),
                                DepartamentoModel.class
                );

        data.addAttribute("departamentoAtual", departamentoExistente);

        DepartamentoModel arrayDepartamentos[] = new Gson()
                .fromJson(
                        Unirest
                                .get("http://localhost:9000/servico/departamento")
                                .asJson()
                                .getBody()
                                .toString(),
                            DepartamentoModel[].class
                );

        data.addAttribute("departamentos", arrayDepartamentos);

        return "departamento-view-alterar";
    }

    @PostMapping ("/departamento/alterar")
    public String alterar (DepartamentoModel departamentoAlterado) throws UnirestException {

        Unirest
                .put("http://localhost:9000/servico/departamento/{id}")
                .routeParam("id", String.valueOf(departamentoAlterado.getId()))
                .header("Content-type", "application/json")
                .header("accept", "application/json")
                .body(new Gson().toJson(departamentoAlterado, DepartamentoModel.class))
                .asJson();

        return "redirect:/departamento";
    }

}
