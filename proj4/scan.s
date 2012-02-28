	.file	"scan.c"
	.section	".text"
	.align 4
	.global scan
scan:
	!#PROLOGUE# 0
	save	%sp, -112, %sp
	!#PROLOGUE# 1
	cmp	%i1, 0
	ble	.LL1
	mov	0, %o0
	sll	%i1, 2, %l0		! shift left logical
	call	malloc, 0
	add	%l0, 4, %o0
	mov	0, %o3
	st	%g0, [%o0+%l0]
	cmp	%o3, %i1
	bge	.LL1
	mov	0, %o2
.LL12:
	sll	%o3, 2, %o4
	ld	[%i0+%o4], %o5
	ld	[%o0+%l0], %g1
	add	%o3, 1, %o3
	cmp	%o5, %g1
	ble	.LL6
	add	%o2, %o5, %o2
	st	%o5, [%o0+%l0]
.LL6:
	cmp	%o3, %i1
	bl	.LL12
	st	%o2, [%o0+%o4]
.LL1:
	ret
	restore %g0, %o0, %o0
	.size	scan, .-scan
	.ident	"GCC: (GNU) 3.4.3 (csl-sol210-3_4-branch+sol_rpath)"
