package ti.testes;

import ti.dao.DAO;
import ti.modelo.Usuario;

public class TestaUsuario {
	public static void main(String[] args) {
		Usuario usuario = new Usuario();
		usuario.setLogin("fabio");
		usuario.setSenha("123456");
		
		DAO<Usuario> daoUsuario = new DAO<Usuario>(Usuario.class);
		daoUsuario.adiciona(usuario);
	}
}
