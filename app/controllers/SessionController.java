package controllers;

import models.Session;
import play.mvc.Controller;
import play.mvc.Result;

public class SessionController extends Controller {

	public static Result GO_HOME = redirect(
        routes.SessionController.list(0, "name", "asc", "")
    );
	
	public static Result list(int page, String sortBy, String order, String filter) {
        return ok(
            views.html.sessions.render(
                Session.page(page, 10, sortBy, order, filter),
                sortBy, order, filter
            )
        );
    }
//	
//	public static ResuColt edit(Long id) {
//        Form<Session> officeForm = form(Session.class).fill(
//            Session.find.byId(id)
//        );
//        return ok(
//            editForm.render(id, officeForm)
//        );
//    }
//    
//    /**
//     * Handle the 'edit form' submission 
//     *
//     * @param id Id of the office to edit
//     */
//    public static Result update(Long id) {
//        Form<Session> officeForm = form(Session.class).bindFromRequest();
//        if(officeForm.hasErrors()) {
//            return badRequest(editForm.render(id, officeForm));
//        }
//        officeForm.get().update(id);
//        flash("success", "Office " + officeForm.get().name + " has been updated");
//        return GO_HOME;
//    }
//    
//    /**
//     * Display the 'new office form'.
//     */
//    public static Result create() {
//        Form<Session> officeForm = form(Session.class);
//        return ok(
//            createForm.render(officeForm)
//        );
//    }
//    
//    /**
//     * Handle the 'new office form' submission 
//     */
//    public static Result save() {
//        Form<Session> officeForm = form(Session.class).bindFromRequest();
//        if(officeForm.hasErrors()) {
//            return badRequest(createForm.render(officeForm));
//        }
//        officeForm.get().save();
//        flash("success", "Office " + officeForm.get().name + " has been created");
//        return GO_HOME;
//    }
//    
//    /**
//     * Handle office deletion
//     */
//    public static Result delete(Long id) {
//        Session.find.ref(id).delete();
//        flash("success", "Office has been deleted");
//        return GO_HOME;
//    }
//    

}
