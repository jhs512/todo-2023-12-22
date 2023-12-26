<script lang="ts">
	import rq from '$lib/rq/rq.svelte';
	import { page } from '$app/stores';
	import type { components } from '$lib/types/api/v1/schema';

	let post: components['schemas']['PostDto'] | null = $state(null);

	rq.effect(async () => {
		const { data, error } = await rq.apiEndPoints().GET('/api/v1/posts/{id}', {
			params: {
				path: {
					id: parseInt($page.params.id)
				}
			}
		});

		if (data) post = data.data.item;
	});
</script>

{#if post}
	<div>
		<h1>{post.title}</h1>
		<p>{post.body}</p>
	</div>
{/if}
