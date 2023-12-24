<script lang="ts">
	let names = $state([]);

	$effect(() => {
		fetch(`${import.meta.env.VITE_CORE_API_BASE_URL}/api/v1/home/names`, {})
			.then((res) => res.json())
			.then((data) => {
				names = data;
			});
	});
</script>

<h1>이름들</h1>

<button class="btn btn-primary">버튼</button>

<ul>
	{#each names as name}
		<li>{name}</li>
	{/each}
</ul>
