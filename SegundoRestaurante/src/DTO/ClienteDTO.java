package DTO;

/**
 *
 * @author Camille
 */
public class ClienteDTO {

    private int id_cliente;
    private String nome_cliente, cpf_cliente, telefone_cliente, email_cliente;

    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    public String getNome_cliente() {
        return nome_cliente;
    }

    public void setNome_cliente(String nome_cliente) {
        this.nome_cliente = nome_cliente;
    }

    public String getCpf_cliente() {
        return cpf_cliente;
    }

    public void setCpf_cliente(String cpf_cliente) {
        this.cpf_cliente = cpf_cliente;
    }

    public String getFone_cliente() {
        return telefone_cliente;
    }

    public void setFone_cliente(String fone_cliente) {
        this.telefone_cliente = fone_cliente;
    }

    public String getEmail_cliente() {
        return email_cliente;
    }

    public void setEmail_cliente(String email_cliente) {
        this.email_cliente = email_cliente;
    }

}
