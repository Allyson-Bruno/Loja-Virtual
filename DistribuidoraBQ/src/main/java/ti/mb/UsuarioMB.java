package ti.mb;

import java.io.Serializable;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import ti.dao.UsuarioDAO;
import ti.modelo.Usuario;

@Named
@SessionScoped
public class UsuarioMB implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Usuario usuario = new Usuario();

	public boolean isUsuarioLogado() {
		return usuario.getLogin() != null;
	}
	
	public String efetuarLogin() {
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		
		if (usuarioDAO.validaLogin(this.usuario)) {
			return "produto?faces-redirect=true";
		}else {
			this.usuario = new Usuario();
			return "login?faces-redirect=true";
		}
	}
			
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}	
}
