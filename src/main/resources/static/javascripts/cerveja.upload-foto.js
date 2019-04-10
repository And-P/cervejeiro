var Cerva = Cerva || {};

	Cerva.UploadFoto = (function(){
		
		function UploadFoto(){
			
			//os inputs hidden que recebem o nome da foto e o contentType
			this.inputNomeFoto = $('input[name=foto]');
			this.inputContentType = $('input[name=contentType]');
			
			//pega o template (div) da foto a ser adicionada e deixa disponível para o Handlebars
			this.htmlFotoCervejaTemplate = $('#foto-cerveja').html();
			this.template = Handlebars.compile(this.htmlFotoCervejaTemplate);
			
			//containers que serão substituidos um pelo outro
			this.containerFotoCerveja = $('.js-container-foto-cerveja');
			this.uploadDrop = $('#upload-drop');
		
		}
		
		UploadFoto.prototype.iniciar = function(){
			var settings = {
					type: 'json',
					filelimit: 1,
					allow: '*.(jpg|jpeg|png)',
					action: this.containerFotoCerveja.data('url-fotos'),
					complete: onUploadCompleto.bind(this),
					beforeSend: adicionarCsrfToken
			}
			
			UIkit.uploadSelect($('#upload-select'), settings);
			UIkit.uploadDrop(this.uploadDrop, settings);
			
			//faz com que a foto fique na tela mesmo enquanto o formulário tem erro de validação
			if(this.inputNomeFoto.val()){
				onUploadCompleto.call(this, {nome: this.inputNomeFoto.val(), contentType: this.inputContentType.val()});	
			}
		}
		
		function onUploadCompleto(resposta){
			this.inputNomeFoto.val(resposta.nome);
			this.inputContentType.val(resposta.contentType);
			
			this.uploadDrop.addClass('hidden');
			var htmlFotoCerveja = this.template({nomeFoto: resposta.nome});
			this.containerFotoCerveja.append(htmlFotoCerveja);
			
			$('.js-remove-foto').on('click', onRemoverFoto.bind(this));
		}
		
		function onRemoverFoto() {
			$('.js-foto-cerveja').remove();
			this.uploadDrop.removeClass('hidden');
			this.inputNomeFoto.val('');
			this.inputContentType.val('');
		}
		
		function adicionarCsrfToken(xhr){
			var token = $('input[name=_csrf]').val();
			var header = $('input[name=_csrf_header]').val();
			xhr.setRequestHeader(header, token);
		}
		
		return UploadFoto; 
		
	})();
	
$(function(){
	var uploadFoto = new Cerva.UploadFoto();
		uploadFoto.iniciar();  
});