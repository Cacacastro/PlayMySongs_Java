package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Carlos
 */
@WebServlet(name = "ServletGeraMusica", urlPatterns = {"/ServletGeraMusica"})
public class ServletGeraMusica extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
       HttpSession sessao = request.getSession(true);
        String musica = new String();
       try{
          musica = request.getParameter("musica");
       }
       catch(Exception e)
       {musica = "§";}
       response.setContentType("text/html;charset=UTF-8");
       try (PrintWriter out = response.getWriter()) {
           out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Gera Musica</title>");            
            out.println("</head>");
            out.println("<body>");
            RandomAccessFile raf;
            raf = new RandomAccessFile(request.getServletContext().getRealPath("")+"//musicas.txt","r");
            boolean flag = false;
            String linha = raf.readLine();
            musica = musica.toLowerCase();
            String min;
            while(linha != null)
            {
                min = linha.toLowerCase();
                if(min.contains(musica))
                {
                    out.println("<p class=\"text-whitesmoke\">"+linha.split("#")[0]+" - "+linha.split("#")[2]+"</p>");
                    out.println("<audio controls>");
                    out.println("<source src='"+linha.split("#")[1]+"'/>");
                    out.println("</audio>");
                    flag = true;
                }     
                linha = raf.readLine();
            }
            raf.close();
            if(flag == false)
                out.println("<p class=\"text-whitesmoke\"> Nenhuma música encontrada com esses paramêtros");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
