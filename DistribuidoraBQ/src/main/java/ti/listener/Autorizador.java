package ti.listener;

import jakarta.faces.application.NavigationHandler;
import jakarta.faces.context.FacesContext;
import jakarta.faces.event.PhaseEvent;
import jakarta.faces.event.PhaseId;
import jakarta.faces.event.PhaseListener;
import ti.mb.UsuarioMB;

public class Autorizador implements PhaseListener {

	@Override
	public void afterPhase(PhaseEvent event) {
		FacesContext context = event.getFacesContext();
		
		 if ("/login.xhtml".equals(context.getViewRoot().getViewId())) {
			 return;
		 }
		
		 // Obtendo LoginBean da sessão
		 UsuarioMB usuarioMB = context.getApplication().evaluateExpressionGet
				 (context, "#{usuarioMB}", UsuarioMB.class);
		 
		 if (!usuarioMB.isUsuarioLogado()) {
			 NavigationHandler handler = context.getApplication().getNavigationHandler();

			 handler.handleNavigation(context, null, "login?faces-redirect=true");

			 context.renderResponse();
		 }
	}

	@Override
	public PhaseId getPhaseId() {
		// TODO Auto-generated method stub
		return PhaseId.RESTORE_VIEW;
	}

}
