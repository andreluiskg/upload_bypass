import java.util.List;

import org.jboss.resteasy.reactive.PartType;
import org.jboss.resteasy.reactive.RestForm;
import org.jboss.resteasy.reactive.multipart.FileUpload;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/api-service")
public class ApiServiceResource {

	@POST
	@Path("v1/receivefiles")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(MediaType.TEXT_PLAIN)
	public Response receiveFiles(
			@RestForm(FileUpload.ALL) @PartType(MediaType.APPLICATION_OCTET_STREAM) List<FileUpload> files) {
		// Retorna uma resposta com a lista de nomes dos arquivos recebidos

		System.out.println(
				"-== Recebido em /api-service/v1/receivefiles (MultipartForm) - " + files.size() + " arquivos ==-");

		StringBuilder arquivos = new StringBuilder();
		for (FileUpload file : files) {
			arquivos.append("-----\nname: " + file.name() + "\n");
			arquivos.append("filename: " + file.fileName() + "\n");
			arquivos.append("charSet: " + file.charSet() + "\n");
			arquivos.append("contentType: " + file.contentType() + "\n");
			arquivos.append("hashCode: " + file.hashCode() + "\n");
			arquivos.append("size: " + file.size() + "\n");
			arquivos.append("toString: " + file.toString() + "\n");
			arquivos.append("uploadedFile: " + file.uploadedFile() + "\n");
		}

		System.out.println(arquivos);

		return Response.ok("-== Recebido em /api-service/v1/receivefiles (MultipartForm) - " + files.size()
				+ " arquivos ==-\n" + arquivos).build();

	}

}
