package servlets;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class JogarNovamente extends HttpServlet {

    public static final String ID_NUMERO_SECRETO = "numero_secreto";
    public static final String ID_TENTATIVA = "tentativa";
    public static final String ID_NUM_TENTATIVAS = "num_tentativas";
    public static final String ID_MSG_ERRO = "msg_erro";
    public static String melhorJogada = "nenhuma";

    private int obterNumeroSecreto(HttpServletRequest request) {
        HttpSession sessao = request.getSession();
        if (sessao.getAttribute(ID_NUMERO_SECRETO) == null) {
            sessao.setAttribute(ID_NUMERO_SECRETO, "" + (int) (Math.random() * 100));
        }
        return Integer.parseInt((String) sessao.getAttribute(ID_NUMERO_SECRETO));
    }

    private void incrementarNumeroDeTentativas(HttpServletRequest request) {
        HttpSession sessao = request.getSession();
        if (sessao.getAttribute(ID_NUM_TENTATIVAS) == null) {
            sessao.setAttribute(ID_NUM_TENTATIVAS, "1");
        } else {
            int numTentativas = Integer.parseInt((String) sessao.getAttribute(ID_NUM_TENTATIVAS));
            numTentativas++;
            sessao.setAttribute(ID_NUM_TENTATIVAS, "" + numTentativas);
        }
    }
    
    private void incrementarMelhorJogada (HttpServletRequest request) {
        HttpSession sessao = request.getSession();    

        if (sessao.getAttribute(melhorJogada) == null) {
            sessao.setAttribute(melhorJogada, sessao.getAttribute(ID_NUM_TENTATIVAS));
            System.out.println("CERTOOOOOOOO");
        } else {
            int numTentativas = Integer.parseInt((String) sessao.getAttribute(ID_NUM_TENTATIVAS));
            int melhor_jogada = Integer.parseInt((String) sessao.getAttribute(melhorJogada));
            if (melhor_jogada > numTentativas){
                melhor_jogada = numTentativas;
            }
            sessao.setAttribute(melhorJogada, "" +melhor_jogada);
        }
    }
    
    private void alterarMensagemDeErro(HttpServletRequest request, String msg) {
        HttpSession sessao = request.getSession();
        
        // criar uma nova servlet e anular os atributos, numero secreto e numero de tentativas
       // sessao.setAttribute(ID_NUM_TENTATIVAS, null);
        //sessao.setAttribute(ID_NUMERO_SECRETO, null);
        sessao.setAttribute(ID_MSG_ERRO, msg);
    }
    
    private void zerar (HttpServletRequest request){
        HttpSession sessao = request.getSession();
        sessao.setAttribute(ID_NUM_TENTATIVAS, null);
        sessao.setAttribute(ID_NUMERO_SECRETO, null);        
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        zerar(request);
        incrementarNumeroDeTentativas(request);
        int tentativa = Integer.parseInt(request.getParameter(ID_TENTATIVA));
        int numeroSecreto = obterNumeroSecreto(request);
        System.out.println(numeroSecreto);
        if (tentativa != numeroSecreto) {
            if (tentativa > numeroSecreto) {
                alterarMensagemDeErro(request, "O número que pensei é MENOR do que esse..." +numeroSecreto+ "");
            } else {
                alterarMensagemDeErro(request, "O número que pensei é MAIOR do que esse..." +numeroSecreto+ "");
            }
            response.sendRedirect("errou.jsp");
        } else {
            incrementarMelhorJogada(request);
            response.sendRedirect("acertou.jsp");            
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}
