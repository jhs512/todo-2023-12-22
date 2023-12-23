<script lang="ts">
	let names = $state([]);

	$effect(() => {
		fetch(`${import.meta.env.VITE_CORE_API_BASE_URL}/home/names`)
			.then((res) => res.json())
			.then((data) => {
				names = data;
			});
	});
</script>

<h1>이름들</h1>

<ul>
	{#each names as name}
		<li>{name}</li>
	{/each}
</ul>
