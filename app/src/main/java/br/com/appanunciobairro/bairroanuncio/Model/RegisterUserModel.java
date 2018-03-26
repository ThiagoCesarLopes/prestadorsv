package br.com.appanunciobairro.bairroanuncio.Model;

public class RegisterUserModel {


        public String flg_estado; //Image URL
        public String descricaoEstado; //Name

        private String nome;
        private String sobreNome;
        private String telefone;
        private String celular;
        private String endereco;
        private String numero;
        private String complemento;
        private String email;
        private String password;
        private String confirmapassword;


        public String getNome() {
                return nome;
        }

        public void setNome(String nome) {
                this.nome = nome;
        }

        public String getSobreNome() {
                return sobreNome;
        }

        public void setSobreNome(String sobreNome) {
                this.sobreNome = sobreNome;
        }

        public String getcelular() {
                return celular;
        }

        public void setcelular(String celular) {
                this.celular = celular;
        }

        public String gettelefone() {
                return telefone;
        }

        public void settelefone(String telefone) {
                this.telefone = telefone;
        }

        public String getendereco() {
                return endereco;
        }

        public void setendereco(String endereco) {
                this.endereco = endereco;
        }

        public String getnumero() {
                return numero;
        }

        public void setnumero(String numero) {
                this.numero = numero;
        }

        public String getcomplemento() {
                return complemento;
        }

        public void setcomplemento(String complemento) {
                this.complemento = complemento;
        }

        //Email

        public String getemail() {
                return email;
        }

        public void setemail(String email) {
                this.email = email;
        }

        //Password

        public String getpassword() {
                return password;
        }

        public void setpassword(String password) {
                this.password = password;
        }

        //Confirma Password

        public String getconfirmapassword() {
                return confirmapassword;
        }

        public void setconfirmapassword(String confirmapassword) {
                this.confirmapassword = confirmapassword;
        }

        public String getflg_estado() {
                return flg_estado;
        }

        public void setflg_estado(String flg_estado)
        {
                this.flg_estado = flg_estado;
        }

        public String getdescricaoEstado() {
                return descricaoEstado;
        }

        public void setdescricaoEstado(String descricaoEstado)
        {
                this.descricaoEstado = descricaoEstado;
        }

}