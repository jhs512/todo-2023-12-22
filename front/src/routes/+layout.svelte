<script lang="ts">
	import '$lib/app.css';
	import rq from '$lib/rq/rq.svelte';
	import { untrack } from 'svelte';

	const { children } = $props();

	$effect(() => {
		untrack(() => {
			rq.initAuth();
		});
	});
</script>

<header class="flex">
	<ul class="flex gap-2">
		<li>
			<a href="/" class="btn btn-link">홈</a>
		</li>
		{#if rq.isLogout()}
			<li>
				<a href="/member/login" class="btn btn-link">로그인</a>
			</li>
			<li>
				<a
					href={`${
						import.meta.env.VITE_CORE_API_BASE_URL
					}/member/socialLogin/kakao?redirectUri=${encodeURIComponent(
						import.meta.env.VITE_CORE_FRONT_BASE_URL + '/?rand=' + Math.random()
					)}`}
					class="btn btn-link"
				>
					카카오 로그인
				</a>
			</li>
		{/if}
		{#if rq.isLogin()}
			<li>
				<button on:click={() => rq.logout()} class="btn btn-link">로그아웃</button>
			</li>
		{/if}
	</ul>

	<div class="flex-1"></div>

	<div>
		<a href="/member/me" class="btn btn-link">
			{rq.member.username}
		</a>
	</div>
</header>

<hr />

<main>
	{@render children()}
</main>
