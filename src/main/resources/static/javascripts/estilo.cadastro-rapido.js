var Brewer = Brewer || {};

Brewer.EstiloCadastroRapido = (function(){
	
	function EstiloCadastroRapido(){//Construtor - Contem as inicializações
		//faz configurações no modal do cadastro rápido de estilo em CadastroCerveja.html
		this.modal = $('#modalCadastroRapidoEstilo'); //recebe o id do modal
		this.form = this.modal.find('form');//recebe o formulario do modal
		this.url = this.form.attr('action');
		this.botaoSalvar = this.modal.find('.js-modal-cadastro-estilo-salvar-btn');//recebe o botao salvar do modal
		this.inputNomeEstilo = $('#nomeEstilo');
		this.containerMensagemErro = $('.js-mensagem-cadastro-rapido-estilo');
	}
	
	EstiloCadastroRapido.prototype.iniciar = function(){
		this.form.on('submit', function(event){event.preventDefault()});//desabilita a ação do botão ao clicar enter
		this.modal.on('shown.bs.modal', onModalShow.bind(this));//o bind traz a função onModalShow para o escopo de construtor
		this.modal.on('hide.bs.modal', onModalClose.bind(this));
		this.botaoSalvar.on('click', onBotaoSalvarClick.bind(this));
		
	}
	
		function onModalShow(){
			this.inputNomeEstilo.focus();
		}

		function onModalClose(){
			this.inputNomeEstilo.val('');
			this.containerMensagemErro.addClass('hidden');
			this.form.find('.form-group').removeClass('has-error');
		}
	
		function onBotaoSalvarClick(){
			var nomeEstilo = this.inputNomeEstilo.val().trim();
			$.ajax({
				url: this.url,
				method: 'POST',
				contentType: 'application/json',
				data: JSON.stringify({ nome: nomeEstilo }),
				error: onErroSalvandoEstilo.bind(this),
				success: onEstiloSalvo.bind(this)
			});
		}
		
		function onErroSalvandoEstilo(obj) {
			var mensagemErro = obj.responseText;
			this.containerMensagemErro.removeClass('hidden');
			this.containerMensagemErro.html('<span>' + mensagemErro + '</span>');
			this.form.find('.form-group').addClass('has-error');
				onModalShow.call(this);
		}
		
		function onEstiloSalvo(estilo) {
			var comboEstilo = $('#estilo');
			comboEstilo.append('<option value=' + estilo.codigo + '>' + estilo.nome + '</option>');
			comboEstilo.val(estilo.codigo);
			this.modal.modal('hide');
		}
	return EstiloCadastroRapido;
	
}());

$(function(){
	var estiloCadastroRapido = new Brewer.EstiloCadastroRapido();
	
	estiloCadastroRapido.iniciar(); 
		
});






