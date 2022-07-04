package kz.halykacademy.webservice;

import java.io.*;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import org.json.JSONObject;

@WebServlet(name = "helloServlet", value = "/organization")
public class Servlet extends HttpServlet {
    private final Map<Long, Organization> organizations = new HashMap<>();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        JSONObject json = new JSONObject();

        json.put("status", "Success");
        json.put("data", new JSONObject(this.organizations));
        json.put("message", "Organizations were returned successfully");

        out.println(json);
    }

    public void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();

        String title = request.getParameter("title");
        String address = request.getParameter("address");
        String creationDate = request.getParameter("creationDate");

        JSONObject json = new JSONObject();
        Organization organization;
        try {
            organization = new Organization(title, address, creationDate);
            this.organizations.put(organization.getId(), organization);

            json.put("status", "Success");
            json.put("data", new JSONObject(organization));
            json.put("message", "Organization was deleted successfully");
        } catch (ParseException e) {
            json.put("status", "Failed");
            json.put("data", JSONObject.NULL);
            json.put("message", e.getMessage());
        }

        out.println(json);
    }

    public void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        JSONObject json = new JSONObject();

        Long id = Long.valueOf(request.getParameter("id"));
        try {
            Organization deletedOrganization = this.organizations.remove(id);

            json.put("status", "Success");
            json.put("data", new JSONObject(deletedOrganization));
            json.put("message", "Organization was deleted successfully");
        } catch (NullPointerException e) {
            json.put("status", "Failed");
            json.put("data", JSONObject.NULL);
            json.put("message", e.getMessage());
        }

        out.println(json);
    }

    public void destroy() {
    }
}