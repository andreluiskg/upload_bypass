import java.util.List;

import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.resteasy.reactive.PartType;
import org.jboss.resteasy.reactive.RestForm;
import org.jboss.resteasy.reactive.multipart.FileUpload;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/v1/upload")
public class UploadResource {

	@Inject
	@RestClient
	ApiService apiService;

	@POST
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(MediaType.TEXT_PLAIN)
	public Response uploadFiles(
			@RestForm(FileUpload.ALL) @PartType(MediaType.APPLICATION_OCTET_STREAM) List<FileUpload> files) {
		// Retorna uma resposta com a lista de nomes dos arquivos recebidos

		System.out.println("-== Recebido em /v1/upload (MultipartForm) - " + files.size() + " arquivos ==-");

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

		System.out.println(
				"-== Enviando para /api-service/v1/receivefiles (MultipartForm) - " + files.size() + "arquivos ==-");
		apiService.receiveFiles(files);
		System.out.println(
				"-== Enviado para /api-service/v1/receivefiles (MultipartForm) - " + files.size() + " arquivos ==-");

		return Response.ok("-== Recebido em /v1/upload (MultipartForm) - " + files.size() + " arquivos ==-\n" + arquivos
				+ "\n-== Enviado para /api-service/v1/receivefiles (MultipartForm) - " + files.size() + " arquivos =-")
				.build();

	}

}
