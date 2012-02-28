	.section	".text"
	.align 4
	.global scan
scan:
	save	%sp, -112, %sp		! allocate a frame
	cmp	%i1, 0			! (n <= 0) assuming n is 2nd parameter
	ble	.LL1			! jump to Label 1 when true
	mov	0, %o0			! delayed branch execution of return 0
	sll	%i1, 2, %l0		! shift left logical
	call	malloc, 0
	add	%l0, 4, %o0
	mov	0, %o3
	st	%g0, [%o0+%l0]
	cmp	%o3, %i1
	bge	.LL1
	mov	0, %o2
.LL2:
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
	bl	.LL2
	st	%o2, [%o0+%o4]
.LL1:
	ret
	restore %g0, %o0, %o0
