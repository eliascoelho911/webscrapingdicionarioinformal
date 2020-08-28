package br.com.eliascoelho911.webscrapingdicionarioinformal.controller

import br.com.eliascoelho911.webscrapingdicionarioinformal.model.Definicao
import br.com.eliascoelho911.webscrapingdicionarioinformal.repository.DefinicaoRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
class DefinicaoController @Autowired constructor(
        private val repository: DefinicaoRepository
) {
    @RequestMapping("/definicao/{valor}")
    fun definicao(@PathVariable("valor") valor: String) : List<Definicao> {
        return repository.buscaDefinicao(valor)
    }
}