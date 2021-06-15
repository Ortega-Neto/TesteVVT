package com.example.testevvt

import androidx.lifecycle.Observer
import com.example.testevvt.apiUtils.DigimonApi
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.*
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.*
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import java.lang.Exception

class MainControllerTest {
    private lateinit var urlBase: String
    private lateinit var mainController: MainController

    private val mainThreadSurrogate = newSingleThreadContext("UI thread")

    @Mock
    private lateinit var respostaObserver: Observer<Boolean>

    @Mock
    private lateinit var erroObserver: Observer<String>
    
    @Before
    fun setup(){
        MockitoAnnotations.initMocks(this)
        Dispatchers.setMain(mainThreadSurrogate)

        urlBase = "digimon-api.vercel.app"
        mainController = MainController()
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        mainThreadSurrogate.close()
    }

    @Test
    fun erroAoBuscarDigimonsResposta(){
        val resultado = Exception("Erro ao Buscar Digimons")
        mainController.onBuscarDigimonError(resultado)

        respostaObserver.onChanged(mainController._respostaSalva)

        verify(respostaObserver).onChanged(true)
    }

    @Test
    fun erroAoBuscarDigimonsMensagem(){
        val resultado = Exception("Erro ao Buscar Digimons")
        mainController.onBuscarDigimonError(resultado)

        erroObserver.onChanged(mainController._erro)

        verify(erroObserver).onChanged("Erro ao Buscar Digimons")
    }

    @Test
    fun erroAoBuscarDigimonsMensagemErrado(){
        val resultado = Exception("Erro ao Buscar")
        mainController.onBuscarDigimonError(resultado)

        Assert.assertNotEquals(mainController._erro, "Erro ao Buscar Digimons")
    }

    @Test
    fun buscarDigimons(){
        CoroutineScope(Dispatchers.Default).launch{
            val resposta = DigimonApi().buscarDigimons(urlBase)

            verify(resposta).isSuccessful
        }
    }

    @Test
    fun buscarDigimonsEVerificarPrimeiro(){
        CoroutineScope(Dispatchers.Default).launch{
            val resposta = DigimonApi().buscarDigimons(urlBase)
            val koromon = resposta.body()!![0]
            Assert.assertEquals(
                koromon,
                Digimon(
                    "Koromon",
                    "https://digimon.shadowsmith.com/img/koromon.jpg",
                    "In Training"
                )
            )
        }
    }

    @Test
    fun buscarDigimonsErro(){
        CoroutineScope(Dispatchers.Default).launch {
            val resposta = DigimonApi().buscarDigimons("urlerrada")

            Assert.assertNotEquals(resposta, resposta.isSuccessful)
        }
    }

    @Test
    fun buscarDigimonsResposta() {
        CoroutineScope(Dispatchers.Default).launch{
            mainController = MainController()

            mainController.buscarDigimons(urlBase)

            respostaObserver.onChanged(mainController._respostaSalva)

            verify(respostaObserver).onChanged(true)
        }
    }
}