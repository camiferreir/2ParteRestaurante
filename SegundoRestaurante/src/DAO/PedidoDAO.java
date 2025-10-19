
package DAO;

import DTO.PedidoDTO;
import VIEWS.TelaPedido;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Camille
 */
public class PedidoDAO {
    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

  
    public void inserirPedido(PedidoDTO objPedidoDTO) {
        String sql = "INSERT INTO pedidos (id_cliente, data_pedido, valor_total) VALUES (?, ?, ?)";
        conexao = ConexaoDAO.conector();

        try {
            pst = conexao.prepareStatement(sql);
            pst.setInt(1, objPedidoDTO.getId_cliente());
            pst.setString(2, objPedidoDTO.getData_pedido());
            pst.setDouble(3, objPedidoDTO.getValor_total());
            int add = pst.executeUpdate();

            if (add > 0) {
                pesquisarAuto();
                limparCampos();
                JOptionPane.showMessageDialog(null, "Pedido inserido com sucesso!");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao inserir pedido: " + e.getMessage());
        }
    }


    public void pesquisar(PedidoDTO objPedidoDTO) {
        String sql = "SELECT * FROM pedidos WHERE id_pedido = ?";
        conexao = ConexaoDAO.conector();

        try {
            pst = conexao.prepareStatement(sql);
            pst.setInt(1, objPedidoDTO.getId_pedido());
            rs = pst.executeQuery();

            if (rs.next()) {
                TelaPedido.txtIdCliente.setText(String.valueOf(rs.getInt("id_cliente")));
                TelaPedido.txtDataPedido.setText(rs.getString("data_pedido"));
                TelaPedido.txtValorTotal.setText(String.valueOf(rs.getDouble("valor_total")));
            } else {
                JOptionPane.showMessageDialog(null, "Pedido não encontrado!");
                limparCampos();
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao pesquisar pedido: " + e.getMessage());
        }
    }

    public void editar(PedidoDTO objPedidoDTO) {
        String sql = "UPDATE pedidos SET id_cliente = ?, data_pedido = ?, valor_total = ? WHERE id_pedido = ?";
        conexao = ConexaoDAO.conector();

        try {
            pst = conexao.prepareStatement(sql);
            pst.setInt(1, objPedidoDTO.getId_cliente());
            pst.setString(2, objPedidoDTO.getData_pedido());
            pst.setDouble(3, objPedidoDTO.getValor_total());
            pst.setInt(4, objPedidoDTO.getId_pedido());

            int upd = pst.executeUpdate();
            if (upd > 0) {
                JOptionPane.showMessageDialog(null, "Pedido atualizado com sucesso!");
                pesquisarAuto();
                limparCampos();
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao editar pedido: " + e.getMessage());
        }
    }

    public void excluir(PedidoDTO objPedidoDTO) {
        String sql = "DELETE FROM pedidos WHERE id_pedido = ?";
        conexao = ConexaoDAO.conector();

        try {
            pst = conexao.prepareStatement(sql);
            pst.setInt(1, objPedidoDTO.getId_pedido());

            int del = pst.executeUpdate();
            if (del > 0) {
                JOptionPane.showMessageDialog(null, "Pedido excluído com sucesso!");
                pesquisarAuto();
                limparCampos();
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir pedido: " + e.getMessage());
        }
    }


    public void pesquisarAuto() {
        String sql = "SELECT * FROM pedidos";
        conexao = ConexaoDAO.conector();

        try {
            pst = conexao.prepareStatement(sql);
            rs = pst.executeQuery();
            DefaultTableModel model = (DefaultTableModel) TelaPedido.TbPedido.getModel();
            model.setNumRows(0);

            while (rs.next()) {
                int id = rs.getInt("id_pedido");
                int idCliente = rs.getInt("id_cliente");
                String data = rs.getString("data_pedido");
                double valor = rs.getDouble("valor_total");

                model.addRow(new Object[]{id, idCliente, data, valor});
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao listar pedidos: " + e.getMessage());
        }
    }


    public void limparCampos() {
        TelaPedido.txtIdPedido.setText(null);
        TelaPedido.txtIdCliente.setText(null);
        TelaPedido.txtDataPedido.setText(null);
        TelaPedido.txtValorTotal.setText(null);
    }

    }

