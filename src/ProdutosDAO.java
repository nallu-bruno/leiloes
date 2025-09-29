/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Adm
 */
import java.sql.PreparedStatement;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.util.List;
import java.util.ArrayList;

public class ProdutosDAO {

    Connection conn;
    conectaDAO conexao;
    PreparedStatement prep;
    ResultSet resultset;

    public ProdutosDAO() {
        this.conexao = new conectaDAO();
        this.conn = this.conexao.getConexao();
    }

    public void cadastrarProduto(ProdutosDTO produto) {

        String sql = "INSERT INTO produtos (nome,valor, status) VALUES" + "(?, ?, ?)";

        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setString(1, produto.getNome());
            stmt.setInt(2, produto.getValor());
            stmt.setString(3, produto.getStatus());
            stmt.execute();
        } catch (Exception e) {
            System.out.println("Erro ao inserir empresa: " + e.getMessage());

        }

    }
    
    public void vendaProduto(ProdutosDTO produto){
         String sql = "UPDATE produtos SET status = ? WHERE id = ?";
         try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setString(1, produto.getStatus());
            stmt.setInt(2, produto.getId());
            stmt.execute();
        } catch (Exception e) {
            System.out.println("Erro ao inserir empresa: " + e.getMessage());

        }
    
    }
    
    public List<ProdutosDTO> getProdutos() {

        String sql = "SELECT * from produtos WHERE status = ?";

        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setString(1,"A Venda");
            ResultSet rs = stmt.executeQuery();

            List<ProdutosDTO> listaProdutos = new ArrayList<>();

            while (rs.next()) {
                ProdutosDTO produto = new ProdutosDTO();

                produto.setId(rs.getInt("id"));
                produto.setNome(rs.getString(("nome")));
                produto.setValor(rs.getInt("valor"));
                produto.setStatus(rs.getString("status"));
                listaProdutos.add(produto);
            }
            return listaProdutos;

        } catch (Exception e) {
            return null;
        }

    }

    public List<ProdutosDTO> getProdutosVendidos() {
        String sql = "SELECT * FROM produtos WHERE status = ?";
        List<ProdutosDTO> listaProdutos = new ArrayList<>();

        try (PreparedStatement stmt = this.conn.prepareStatement(sql)) {
            stmt.setString(1, "Vendido");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                ProdutosDTO produto = new ProdutosDTO();
                produto.setId(rs.getInt("id"));
                produto.setNome(rs.getString("nome"));
                produto.setValor(rs.getInt("valor"));
                produto.setStatus(rs.getString("status"));

                listaProdutos.add(produto);
            }

        } catch (Exception e) {
            System.out.println("Erro ao buscar produtos: " + e.getMessage());
        }

        return listaProdutos;
    }

}
