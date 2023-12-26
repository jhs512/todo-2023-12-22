<script lang="ts">
	// @ts-ignore
	import Editor from '@toast-ui/editor';
	import '@toast-ui/editor/dist/i18n/ko-kr';
	import '@toast-ui/editor/dist/toastui-editor.css'; // Editor's Style

	$effect(() => {
		const editor = new Editor({
			el: document.querySelector('#editor'),
			height: '500px',
			initialEditType: 'markdown',
			previewStyle: 'vertical',
			language: 'ko-KR'
		});

		editor.getMarkdown();
	});
</script>

<div id="editor"></div>
