<script lang="ts">
	import '$lib/app.css';
	const { children } = $props();
</script>

<div>
	{@render children()}
</div>
