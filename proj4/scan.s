	.file	"scan.c"
	.section	".text"
	.align 4
	.global scan
	.type	scan, #function
	.proc	0104
scan:
	!#PROLOGUE# 0
	save	%sp, -112, %sp
	!#PROLOGUE# 1
	cmp	%i1, 0
	ble	.LL1
	mov	0, %o0
	sll	%i1, 2, %l0
	call	malloc, 0
	add	%l0, 4, %o0
	ld	[%i0], %g1
	st	%g1, [%o0]
	mov	1, %o2
	b	.LL10
	st	%g0, [%o0+%l0]
.LL12:
	ld	[%i0+%o4], %o5
	ld	[%o0+%l0], %g1
	add	%o4, %o0, %o3
	cmp	%o5, %g1
	ble	.LL6
	add	%o2, 1, %o2
	st	%o5, [%o0+%l0]
.LL6:
	ld	[%o3-4], %g1
	add	%o5, %g1, %g1
	st	%g1, [%o0+%o4]
.LL10:
	cmp	%o2, %i1
	bl	.LL12
	sll	%o2, 2, %o4
.LL1:
	ret
	restore %g0, %o0, %o0
	.size	scan, .-scan
	.ident	"GCC: (GNU) 3.4.3 (csl-sol210-3_4-branch+sol_rpath)"
